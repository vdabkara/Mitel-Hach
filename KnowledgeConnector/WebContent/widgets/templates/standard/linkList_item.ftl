<@compress single_line=true>
                <tr id="linkedItemRow_${value.document.answerId}"> 
                    <td><a href="answerView.jsp?answerId=${value.document.answerId}&loc=${localeid}&incidentID=${value.incident.incidentId}">${value.document.documentId}</a></td>
                    <td><a href="answerView.jsp?answerId=${value.document.answerId}&loc=${localeid}&incidentID=${value.incident.incidentId}">${value.document.title}</a></td>
                    <td><button 
                    	id="showDialog"
                        data-id="${value.document.answerId}" 
                        data-title="${value.document.title}" 
                        data-recordid="${value.document.recordId}" 
                        data-versionid="${value.document.versionId}" 
                        data-documentid="${value.document.documentId}" 
                        data-version="${value.document.version}" 
                        data-answerid="${value.document.answerId}" 
                        class="removeLink"
                    ></button></td>
                </tr>
</@compress>