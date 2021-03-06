package hach.project.main;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Test {

	public static void main(String[] args) {
		try
		{
			String locale="en_US";
			Vector<JSONObject> allData = getAllCategories();
			Vector<JSONObject> ret = new Vector<JSONObject>();
			if(null!=allData)
			{
				JSONObject obj = null;
				System.out.println(allData.size());
				StringBuilder str = new StringBuilder();
				str.append("LEVEL 1 TYPE$LEVEL 1 REF KEY$LEVEL 1 NAME$LEVEL 1 ID$LEVEL 1 REC ID$");
				str.append("LEVEL 2 TYPE$LEVEL 2 REF KEY$LEVEL 2 NAME$LEVEL 2 ID$LEVEL 2 REC ID$");
				str.append("LEVEL 3 TYPE$LEVEL 3 REF KEY$LEVEL 3 NAME$LEVEL 3 ID$LEVEL 3 REC ID$");
				str.append("LEVEL 4 TYPE$LEVEL 4 REF KEY$LEVEL 4 NAME$LEVEL 4 ID$LEVEL 4 REC ID$");
				str.append("LEVEL 5 TYPE$LEVEL 5 REF KEY$LEVEL 5 NAME$LEVEL 5 ID$LEVEL 5 REC ID");
				
				str.append(System.getProperty("line.separator"));
				
				
				JSONArray parents = new JSONArray();
				JSONObject childObj = null;
				String href=null;
				String parentId=null;
				
				for(int a=0;a<allData.size();a++)
				{
					obj = (JSONObject)allData.get(a);
					if(null!=obj)
					{
//						if(obj.getBoolean("hasChildren")==false)
//						{
//							parents.put(obj);
//							// remove this from ret
//							ret.remove(a);
//							a--;
//						}
//						str.append(obj.get("referenceKey")+"$");
////						str.append(obj.get("objectId")+"$");
//						str.append(obj.get("name")+"$");
//						str.append(obj.get("externalId")+"$");
//						str.append(obj.get("externalType")+"$");
//						str.append(obj.get("hasChildren")+"$");
//						str.append(obj.get("recordId"));
						parentId = "";
						if(null!=obj.getJSONArray("links") && obj.getJSONArray("links").length()>0)
						{
							for(int b=0;b<obj.getJSONArray("links").length();b++)
							{
								if(null!=obj.getJSONArray("links").get(b))
								{
									childObj = (JSONObject)obj.getJSONArray("links").get(b);
									if(null!=childObj)
									{
										if(null!=childObj.get("rel") && childObj.get("rel").equals("parent"))
										{
											// IDENTIFY url AND GET PARENT ID
											if(null!=childObj.get("href"))
											{
												href = childObj.getString("href");
												parentId = href.substring(href.lastIndexOf("/")+1, href.length());
												href=null;
											}
											break;
										}
									}
									childObj = null;
								}
							}
						}
//						str.append(parentId);
//						str.append(System.getProperty("line.separator"));
						obj.put("parentId", parentId);
						parentId = null;
						// add to ret
						ret.add(obj);
					}
					obj = null;
				}
				
				
				// identify parents
				for(int b=0;b<ret.size();b++)
				{
					System.out.println(ret.get(b).toString());
					if(null==ret.get(b).get("parentId") || "".equals(ret.get(b).get("parentId")))
					{
						parents.put(ret.get(b));
						// remove this item from ret
						ret.remove(b);
						b--;
					}
				}
				
				if(null!=parents && null!=ret)
				{
					System.out.println("total parents found are :: >"+ parents.length());
					System.out.println("remaining items are ::>"+ ret.size());
					// identify level 1 childs
					obj = null;
					JSONArray firstLevel = new JSONArray();
					for(int a=0;a<parents.length();a++)
					{
						obj = (JSONObject)parents.getJSONObject(a);
						if(null!=ret && ret.size()>0)
						{
							for(int b=0;b<ret.size();b++)
							{
								if(null!=ret.get(b).get("parentId") && !"".equals(ret.get(b).get("parentId")))
								{
									if(obj.get("recordId").equals(ret.get(b).get("parentId")))
									{
										// add this item as immediate child
										try
										{
											if(null!=obj.getJSONArray("childs") && obj.getJSONArray("childs").length()>0)
											{
												// add to existing array
												obj.getJSONArray("childs").put(ret.get(b));
											}
											else
											{
												firstLevel = new JSONArray();
												firstLevel.put(ret.get(b));
												obj.put("childs", firstLevel);
												firstLevel = null;
											}
										}
										catch(Exception e1)
										{
											firstLevel = new JSONArray();
											firstLevel.put(ret.get(b));
											obj.put("childs", firstLevel);
											firstLevel = null;
										}
										// remove this item from ret
										ret.remove(b);
										b--;
									}
								}
							}
						}
					}
					
					// check for level 2
					if(null!=ret && ret.size()>0)
					{
						// still some items left
						System.out.println("for second level remaining items are :: >"+ ret.size());
						obj = null;
						childObj = null;
						JSONArray secondLevel = new JSONArray();
						for(int a=0;a<parents.length();a++)
						{
							obj = (JSONObject)parents.getJSONObject(a);
							try
							{
								if(null!=obj.getJSONArray("childs") && obj.getJSONArray("childs").length()>0)
								{

									for(int b=0;b<obj.getJSONArray("childs").length();b++)
									{
										childObj = (JSONObject)obj.getJSONArray("childs").getJSONObject(b);
										if(null!=ret && ret.size()>0)
										{
											for(int c=0;c<ret.size();c++)
											{
												if(null!=ret.get(c).get("parentId") && !"".equals(ret.get(c).get("parentId")))
												{
													if(childObj.get("recordId").equals(ret.get(c).get("parentId")))
													{
														// add this item as immediate child
														try
														{
															if(null!=childObj.getJSONArray("childs") && childObj.getJSONArray("childs").length()>0)
															{
																// add to existing array
																childObj.getJSONArray("childs").put(ret.get(c));
															}
															else
															{
																secondLevel = new JSONArray();
																secondLevel.put(ret.get(c));
																childObj.put("childs", secondLevel);
																secondLevel = null;
															}
														}
														catch(Exception E1)
														{
															secondLevel = new JSONArray();
															secondLevel.put(ret.get(c));
															childObj.put("childs", secondLevel);
															secondLevel = null;
														}

														// remove this item from ret
														ret.remove(c);
														c--;
													}
												}
											}
										}
									}
								}
							}
							catch(Exception WQ)
							{
								
							}
							obj = null;
						}
					}
					
					// check for level 3
					if(null!=ret && ret.size()>0)
					{
						// still some items left
						System.out.println("for third level remaining items are :: >"+ ret.size());
						obj = null;
						childObj = null;
						JSONObject subChildObj=  null;
						JSONArray thirdLevel = new JSONArray();
						for(int a=0;a<parents.length();a++)
						{
							obj = (JSONObject)parents.getJSONObject(a);
							try
							{
								if(null!=obj.getJSONArray("childs") && obj.getJSONArray("childs").length()>0)
								{

									for(int b=0;b<obj.getJSONArray("childs").length();b++)
									{
										childObj = (JSONObject)obj.getJSONArray("childs").getJSONObject(b);
										try
										{
											if(null!=childObj.getJSONArray("childs") && childObj.getJSONArray("childs").length()>0)
											{
												for(int c=0;c<childObj.getJSONArray("childs").length();c++)
												{
													subChildObj = childObj.getJSONArray("childs").getJSONObject(c);
													if(null!=ret && ret.size()>0)
													{
														for(int d=0;d<ret.size();d++)
														{
															if(null!=ret.get(d).get("parentId") && !"".equals(ret.get(d).get("parentId")))
															{
																if(subChildObj.get("recordId").equals(ret.get(d).get("parentId")))
																{
																	// add this item as immediate child
																	try
																	{
																		if(null!=subChildObj.getJSONArray("childs") && subChildObj.getJSONArray("childs").length()>0)
																		{
																			// add to existing array
																			subChildObj.getJSONArray("childs").put(ret.get(d));
																		}
																		else
																		{
																			thirdLevel = new JSONArray();
																			thirdLevel.put(ret.get(d));
																			subChildObj.put("childs", thirdLevel);
																			thirdLevel = null;
																		}
																	}
																	catch(Exception E1)
																	{
																		thirdLevel = new JSONArray();
																		thirdLevel.put(ret.get(d));
																		subChildObj.put("childs", thirdLevel);
																		thirdLevel = null;
																	}

																	// remove this item from ret
																	ret.remove(d);
																	d--;
																}
															}
														}
													}
												}
											}
										}
										catch(Exception eq) {}
									}
								}
							}
							catch(Exception WQ)
							{
								
							}
							obj = null;
						}
					
					}
					
					// check for level 4
					if(null!=ret && ret.size()>0)
					{
						// still some items left
						System.out.println("for forth level remaining items are :: >"+ ret.size());

						obj = null;
						childObj = null;
						JSONObject subChildObj=  null;
						JSONObject subSubChildObj = null;
						JSONArray forthLevel = new JSONArray();
						for(int a=0;a<parents.length();a++)
						{
							obj = (JSONObject)parents.getJSONObject(a);
							try
							{
								if(null!=obj.getJSONArray("childs") && obj.getJSONArray("childs").length()>0)
								{
									for(int b=0;b<obj.getJSONArray("childs").length();b++)
									{
										childObj = (JSONObject)obj.getJSONArray("childs").getJSONObject(b);
										try
										{
											if(null!=childObj.getJSONArray("childs") && childObj.getJSONArray("childs").length()>0)
											{
												for(int c=0;c<childObj.getJSONArray("childs").length();c++)
												{
													subChildObj = childObj.getJSONArray("childs").getJSONObject(c);
													try
													{
														if(null!=subChildObj.getJSONArray("childs") && subChildObj.getJSONArray("childs").length()>0)
														{
															for(int d=0;d<subChildObj.getJSONArray("childs").length();d++)
															{
																subSubChildObj = subChildObj.getJSONArray("childs").getJSONObject(d);
																if(null!=ret && ret.size()>0)
																{
																	for(int e=0;e<ret.size();e++)
																	{
																		if(null!=ret.get(e).get("parentId") && !"".equals(ret.get(e).get("parentId")))
																		{
																			if(subSubChildObj.get("recordId").equals(ret.get(e).get("parentId")))
																			{
																				// add this item as immediate child
																				try
																				{
																					if(null!=subSubChildObj.getJSONArray("childs") && subSubChildObj.getJSONArray("childs").length()>0)
																					{
																						// add to existing array
																						subSubChildObj.getJSONArray("childs").put(ret.get(e));
																					}
																					else
																					{
																						forthLevel = new JSONArray();
																						forthLevel.put(ret.get(e));
																						subSubChildObj.put("childs", forthLevel);
																						forthLevel = null;
																					}
																				}
																				catch(Exception E1)
																				{
																					forthLevel = new JSONArray();
																					forthLevel.put(ret.get(e));
																					subSubChildObj.put("childs", forthLevel);
																					forthLevel = null;
																				}

																				// remove this item from ret
																				ret.remove(e);
																				e--;
																			}
																		}
																	}
																}
															}
														}
													}
													catch(Exception e2)
													{}
												}
											}
										}
										catch(Exception eq) {}
									}
								}
							}
							catch(Exception WQ)
							{
								
							}
							obj = null;
						}
					}
					
					System.out.println("---------- rec size :: >"+ ret.size());
					// check for level 5
					if(null!=ret && ret.size()>0)
					{
						// still some items left
						System.out.println("for fifth level remaining items are :: >"+ ret.size());
					}
					else
					{
						// no heirarchy left
						System.out.println("---------- no more hierarchy left -----------------");
					}
				}
				System.out.println("---------- here ------");
				
				if(null!=parents && parents.length()>0)
				{
					JSONObject level1 = null;
					JSONObject level2=null;
					JSONObject level3=null;
					JSONObject level4=null;
					JSONObject level5=null;
					
					/*
					 * ONLY ALLOW THE FOLLOWING HIERARCHY
					 * RN_PRODUCT_1707
						RN_PRODUCT_1705
						RN_PRODUCT_1706
						RN_PRODUCT_1704
						RN_PRODUCT_1701
					 */
					
					JSONArray processedObj = new JSONArray();
					for(int a=0;a<parents.length();a++)
					{
						level1 = (JSONObject) parents.getJSONObject(a);
						System.out.println("Name :: >"+ level1.get("name")+ " >>  "+ level1.get("referenceKey"));
//						if(level1.get("referenceKey").equals("RN_PRODUCT_1707") || level1.get("referenceKey").equals("RN_PRODUCT_1705") || level1.get("referenceKey").equals("RN_PRODUCT_1706") || 
//								level1.get("referenceKey").equals("RN_PRODUCT_1704") || level1.get("referenceKey").equals("RN_PRODUCT_1701") || level1.get("referenceKey").equals("RN_PRODUCT_1918") || 
//								level1.get("referenceKey").equals("RN_CATEGORY_1357") || level1.get("referenceKey").equals("RN_CATEGORY_1925") || level1.get("referenceKey").equals("RN_CATEGORY_1929") || 
//								level1.get("referenceKey").equals("RN_CATEGORY_1933") || level1.get("referenceKey").equals("RN_CATEGORY_1940") || level1.get("referenceKey").equals("RN_CATEGORY_1941"))
						{
							processedObj.put(level1);
						}
						level1 = null;
					}
					
					System.out.println(" ---------- processedObj :: >"+ processedObj.length());
					
					for(int a=0;a<processedObj.length();a++)
					{
						level1 = (JSONObject) processedObj.getJSONObject(a);
						try
						{
							if(null!=level1.getJSONArray("childs") && level1.getJSONArray("childs").length()>0)
							{
								for(int b=0;b<level1.getJSONArray("childs").length();b++)
								{
									level2 = (JSONObject)level1.getJSONArray("childs").getJSONObject(b);
									try
									{
										if(null!=level2.getJSONArray("childs") && level2.getJSONArray("childs").length()>0)
										{
											for(int c=0;c<level2.getJSONArray("childs").length();c++)
											{
												level3 = (JSONObject)level2.getJSONArray("childs").getJSONObject(c);
												try
												{
													if(null!=level3.getJSONArray("childs") && level3.getJSONArray("childs").length()>0)
													{
														for(int d=0;d<level3.getJSONArray("childs").length();d++)
														{
															level4 = (JSONObject)level3.getJSONArray("childs").getJSONObject(d);
															try
															{
																if(null!=level4.getJSONArray("childs") && level4.getJSONArray("childs").length()>0)
																{
																	for(int e=0;e<level4.getJSONArray("childs").length();e++)
																	{
																		level5 = (JSONObject)level4.getJSONArray("childs").getJSONObject(e);
																		// LEVEL 1 DATA
																		str.append(level1.get("externalType")+"$");
																		str.append(level1.get("referenceKey")+"$");
																		str.append(level1.get("name")+"$");
																		str.append(level1.get("externalId")+"$");
																		str.append(level1.get("recordId")+"$");
																		// LEVEL 2 DATA
																		str.append(level2.get("externalType")+"$");
																		str.append(level2.get("referenceKey")+"$");
																		str.append(level2.get("name")+"$");
																		str.append(level2.get("externalId")+"$");
																		str.append(level2.get("recordId")+"$");
																		// LEVEL 3 DATA
																		str.append(level3.get("externalType")+"$");
																		str.append(level3.get("referenceKey")+"$");
																		str.append(level3.get("name")+"$");
																		str.append(level3.get("externalId")+"$");
																		str.append(level3.get("recordId")+"$");
																		// LEVEL 4 DATA
																		str.append(level4.get("externalType")+"$");
																		str.append(level4.get("referenceKey")+"$");
																		str.append(level4.get("name")+"$");
																		str.append(level4.get("externalId")+"$");
																		str.append(level4.get("recordId")+"$");
																		// LEVEL 5 DATA
																		str.append(level5.get("externalType")+"$");
																		str.append(level5.get("referenceKey")+"$");
																		str.append(level5.get("name")+"$");
																		str.append(level5.get("externalId")+"$");
																		str.append(level5.get("recordId"));
																		str.append(System.getProperty("line.separator"));
																	}
																}
																else
																{
																	// LEVEL 1 DATA
																	str.append(level1.get("externalType")+"$");
																	str.append(level1.get("referenceKey")+"$");
																	str.append(level1.get("name")+"$");
																	str.append(level1.get("externalId")+"$");
																	str.append(level1.get("recordId")+"$");
																	// LEVEL 2 DATA
																	str.append(level2.get("externalType")+"$");
																	str.append(level2.get("referenceKey")+"$");
																	str.append(level2.get("name")+"$");
																	str.append(level2.get("externalId")+"$");
																	str.append(level2.get("recordId")+"$");
																	// LEVEL 3 DATA
																	str.append(level3.get("externalType")+"$");
																	str.append(level3.get("referenceKey")+"$");
																	str.append(level3.get("name")+"$");
																	str.append(level3.get("externalId")+"$");
																	str.append(level3.get("recordId")+"$");
																	// LEVEL 4 DATA
																	str.append(level4.get("externalType")+"$");
																	str.append(level4.get("referenceKey")+"$");
																	str.append(level4.get("name")+"$");
																	str.append(level4.get("externalId")+"$");
																	str.append(level4.get("recordId"));
																	str.append(System.getProperty("line.separator"));
																}
															}
															catch(Exception e)
															{
																// LEVEL 4 DATA
																str.append(level1.get("externalType")+"$");
																str.append(level1.get("referenceKey")+"$");
																str.append(level1.get("name")+"$");
																str.append(level1.get("externalId")+"$");
																str.append(level1.get("recordId")+"$");
																// LEVEL 2 DATA
																str.append(level2.get("externalType")+"$");
																str.append(level2.get("referenceKey")+"$");
																str.append(level2.get("name")+"$");
																str.append(level2.get("externalId")+"$");
																str.append(level2.get("recordId")+"$");
																// LEVEL 3 DATA
																str.append(level3.get("externalType")+"$");
																str.append(level3.get("referenceKey")+"$");
																str.append(level3.get("name")+"$");
																str.append(level3.get("externalId")+"$");
																str.append(level3.get("recordId")+"$");
																// LEVEL 4 DATA
																str.append(level4.get("externalType")+"$");
																str.append(level4.get("referenceKey")+"$");
																str.append(level4.get("name")+"$");
																str.append(level4.get("externalId")+"$");
																str.append(level4.get("recordId"));
																str.append(System.getProperty("line.separator"));
															}
														}
													}
													else
													{
														// LEVEL 1 DATA
														str.append(level1.get("externalType")+"$");
														str.append(level1.get("referenceKey")+"$");
														str.append(level1.get("name")+"$");
														str.append(level1.get("externalId")+"$");
														str.append(level1.get("recordId")+"$");
														// LEVEL 2 DATA
														str.append(level2.get("externalType")+"$");
														str.append(level2.get("referenceKey")+"$");
														str.append(level2.get("name")+"$");
														str.append(level2.get("externalId")+"$");
														str.append(level2.get("recordId")+"$");
														// LEVEL 3 DATA
														str.append(level3.get("externalType")+"$");
														str.append(level3.get("referenceKey")+"$");
														str.append(level3.get("name")+"$");
														str.append(level3.get("externalId")+"$");
														str.append(level3.get("recordId"));
														str.append(System.getProperty("line.separator"));
													}
												}
												catch(Exception e)
												{
													// LEVEL 1 DATA
													str.append(level1.get("externalType")+"$");
													str.append(level1.get("referenceKey")+"$");
													str.append(level1.get("name")+"$");
													str.append(level1.get("externalId")+"$");
													str.append(level1.get("recordId")+"$");
													// LEVEL 2 DATA
													str.append(level2.get("externalType")+"$");
													str.append(level2.get("referenceKey")+"$");
													str.append(level2.get("name")+"$");
													str.append(level2.get("externalId")+"$");
													str.append(level2.get("recordId")+"$");
													// LEVEL 3 DATA
													str.append(level3.get("externalType")+"$");
													str.append(level3.get("referenceKey")+"$");
													str.append(level3.get("name")+"$");
													str.append(level3.get("externalId")+"$");
													str.append(level3.get("recordId"));
													str.append(System.getProperty("line.separator"));
												}
											}
										}
										else
										{
											// LEVEL 1 DATA
											str.append(level1.get("externalType")+"$");
											str.append(level1.get("referenceKey")+"$");
											str.append(level1.get("name")+"$");
											str.append(level1.get("externalId")+"$");
											str.append(level1.get("recordId")+"$");
											// LEVEL 2 DATA
											str.append(level2.get("externalType")+"$");
											str.append(level2.get("referenceKey")+"$");
											str.append(level2.get("name")+"$");
											str.append(level2.get("externalId")+"$");
											str.append(level2.get("recordId"));
											str.append(System.getProperty("line.separator"));
										}
									}
									catch(Exception e)
									{
										// LEVEL 1 DATA
										str.append(level1.get("externalType")+"$");
										str.append(level1.get("referenceKey")+"$");
										str.append(level1.get("name")+"$");
										str.append(level1.get("externalId")+"$");
										str.append(level1.get("recordId")+"$");
										// LEVEL 2 DATA
										str.append(level2.get("externalType")+"$");
										str.append(level2.get("referenceKey")+"$");
										str.append(level2.get("name")+"$");
										str.append(level2.get("externalId")+"$");
										str.append(level2.get("recordId"));
										str.append(System.getProperty("line.separator"));
									}
								}
							}
							else
							{
								// LEVEL 1 DATA
								str.append(level1.get("externalType")+"$");
								str.append(level1.get("referenceKey")+"$");
								str.append(level1.get("name")+"$");
								str.append(level1.get("externalId")+"$");
								str.append(level1.get("recordId"));
								str.append(System.getProperty("line.separator"));
							}
						}
						catch(Exception e) 
						{
							// no child exist - append parent row
							str.append(level1.get("externalType")+"$");
							str.append(level1.get("referenceKey")+"$");
							str.append(level1.get("name")+"$");
							str.append(level1.get("externalId")+"$");
							str.append(level1.get("recordId"));
							str.append(System.getProperty("line.separator"));
//							str.append(level1.get("objectId")+"$");
//							str.append(level1.get("hasChildren")+"$");
//							str.append(level1.get("recordId"));
						}
						
					}
				}
				
				if(null!=str) 
				{
					File textFile = new File("D:\\KA_CATEGORIES_"+locale.toUpperCase()+".txt");
					FileOutputStream fos = new FileOutputStream(textFile);
					fos.write(str.toString().replace("null", "").getBytes());
					fos.flush();
					fos.close();
					fos = null;
					textFile = null;
				} 
				str = null;
				
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private static Vector<JSONObject> getAllCategories()
	{
		Vector<JSONObject> ret = new Vector<JSONObject>();

		boolean hasMore = true;
		int limit = 1000;
		int offset = 0;
		OSVCandKAWebServiceCaller caller = new OSVCandKAWebServiceCaller();
		while (hasMore)
		{
			// System.out.println("--------------------------");
			System.out.println("GID content rec batch offset " + offset);

			JSONObject categoriesObject = null;
			try
			{
				// note we can also use the mode=FULL& http parameter, but it omits certain results
				// with {links} type JSON objects. Don't know why exactly.
//				categoriesObject = caller.callKAWebservice(
//						"https://hachcompany-irs.custhelp.com/km/api/latest/categories?limit=" + limit + "&offset=" + offset);
				categoriesObject = caller.callKAWebservice(
						"https://portlandgeneral-irs.custhelp.com/km/api/latest/categories?limit=" + limit + "&offset=" + offset);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			offset += limit;

			// System.out.println("\n\n----- CRMD -----");
			// System.out.println(contentRecommendations);
			// System.out.println("----- CRMD -----\n\n");

			if (categoriesObject == null)
				continue;

			try
			{
				hasMore = categoriesObject.getBoolean("hasMore");
				// get all recordIDs
				JSONArray items = categoriesObject.getJSONArray("items");
				for (int i = 0; i < items.length(); i++)
				{
					JSONObject item = items.getJSONObject(i);
					if(null!=item)
					{
						ret.add(item);
					}
					item = null;
				}
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
		return ret;
	}

	
}
