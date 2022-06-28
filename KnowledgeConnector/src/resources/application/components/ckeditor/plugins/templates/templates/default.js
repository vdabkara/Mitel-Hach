/*
 Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 For licensing, see LICENSE.md or http://ckeditor.com/license
*/
CKEDITOR.addTemplates(
	"default",
	{imagesPath:CKEDITOR.getUrl(CKEDITOR.plugins.getPath("templates")+"templates/images/"),
	templates:[
		{title:"Standard Procedures Template",image:"",description:"Creates a standard procedures template layout",
			html:'<div><h2 class="TOCHeading">Description</h2><p>Text</p><p>&nbsp;</p></div><div><h2 class="TOCHeading">Guidelines</h2><p>Text</p><p>&nbsp;</p></div><div><h2 class="TOCHeading">Endorsement</h2><p>Text</p><p>&nbsp;</p></div><div><h2 class="TOCHeading">Handling Procedures</h2><br /><table class="nw"><tbody><tr><td colspan="2" class="C">Table Title</td></tr><tr><td class="w5 C">Step</td><td class="w95">Action</td></tr><tr><td class="C">1</td><td>&nbsp;</td></tr><tr><td class="C">2</td><td>&nbsp;</td></tr><tr><td class="C">3</td><td>&nbsp;</td></tr><tr><td class="C">4</td><td>&nbsp;</td></tr><tr><td class="C">5</td><td>&nbsp;</td></tr><tr><td class="C">6</td><td>&nbsp;</td></tr><tr><td class="C">7</td><td>&nbsp;</td></tr><tr><td class="C">8</td><td>&nbsp;</td></tr><tr><td class="C">9</td><td>&nbsp;</td></tr><tr><td class="C">10</td><td>&nbsp;</td></tr></tbody></table><p>&nbsp;</p></div>'},
		{title:"General Template",image:"",description:"Creates a general template layout",
			html:'<div><h2 class="TOCHeading">Section 1</h2><p>Text</p><p>&nbsp;</p></div><div><h2 class="TOCHeading">Section 2</h2><p>Text</p><p>&nbsp;</p></div><div><h2 class="TOCHeading">Section 3</h2><p>Text</p><p>&nbsp;</p></div><div><h2 class="TOCHeading">Section 4</h2><p>Text</p><p>&nbsp;</p></div><div><h2 class="TOCHeading">Section 5</h2><p>Text</p><p>&nbsp;</p></div><div><h2 class="TOCHeading">Section 6</h2><p>Text</p><p>&nbsp;</p></div><div><h2 class="TOCHeading">Section 7</h2><p>Text</p><p>&nbsp;</p></div><div><h2 class="TOCHeading">Section 8</h2><p>Text</p><p>&nbsp;</p></div><div><h2 class="TOCHeading">Section 9</h2><p>Text</p><p>&nbsp;</p></div><div><h2 class="TOCHeading">Section 10</h2><p>Text</p><p>&nbsp;</p></div>'},
		{title:"Section",image:"",description:"Creates a section layout",
			html:'<div><h2 class="TOCHeading">Section 1</h2><p>Text</p><p>&nbsp;</p></div>'},
		{title:"Step/Action Table",image:"",description:"Creates a Step/Action table with table title",
			html:'<table class="nw"><tr><td colspan="2" class="C">Table Title</td></tr><tr><td class="w5 C">Step</td><td class="w95">Action</td></tr><tr><td class="C">1</td><td>&nbsp;</td></tr><tr><td class="C">2</td><td>&nbsp;</td></tr><tr><td class="C">3</td><td>&nbsp;</td></tr><tr><td class="C">4</td><td>&nbsp;</td></tr><tr><td class="C">5</td><td>&nbsp;</td></tr><tr><td class="C">6</td><td>&nbsp;</td></tr><tr><td class="C">7</td><td>&nbsp;</td></tr><tr><td class="C">8</td><td>&nbsp;</td></tr><tr><td class="C">9</td><td>&nbsp;</td></tr><tr><td class="C">10</td><td>&nbsp;</td></tr></table>'},
		{title:"If/Then Table 4",image:"",description:"Creates an If/Then table with table title",
			html:'<table class="nw"><tr><td colspan="2"><span class="nw_title">Table Title4</span></td></tr><tr><td class="w15">If ...</td><td class="w85">Then ...</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr></table>'},
		{title:"Collapsible Section",image:"",description:"Text within Table Body will be collapsed with functionality for it to be open and closed.",
			html:'<table class="nw_collapsible" width="100%"><thead><tr><td class="collapsible_title">Title</td></tr></thead><tbody><tr><td class="collapsible_body"><a name="ect_[unique_name]"></a>Body text</td></tr></tbody></table><p>&nbsp;</p>'},
		{title:"Bulleted List - Standard",image:"",description:"Bulleted List Standard template",
			html:'<ul><li>List</li><li>List</li><li>List</li><li>List</li><li>List</li></ul>'},
		{title:"Bulleted List - Template A",image:"",description:"Bulleted List Standard template",
			html:'<ul class="arrowA"><li>List</li><li>List</li><li>List</li><li>List</li><li>List</li></ul>'},
		{title:"Bulleted List - Template B",image:"",description:"Bulleted List Standard template",
			html:'<ul class="arrowB"><li>List</li><li>List</li><li>List</li><li>List</li><li>List</li></ul>'},
		{title:"Bulleted List - Nonactive",image:"",description:"Bulleted List Standard template",
			html:'<ul class="arrow_nonactive"><li>List</li><li>List</li><li>List</li><li>List</li><li>List</li></ul>'},
		{title:"Tooltip Body",image:"",description:"Creates the body layout of a tooltip layout.",
			html:'<table class="nw_tip"><thead><tr><td><a name="dia_[unique_name]"></a></td></tr></thead><tbody><tr><td class="tip_body">Body of Tooltip</td></tr></tbody></table>'}
	]
	}
);