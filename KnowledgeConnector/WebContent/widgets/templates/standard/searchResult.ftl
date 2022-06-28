<#--
Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.

The sample code in this document or accessed through this document is not certified or
supported by Oracle. It is intended for educational or testing purposes only. Use of this
sample code implies acceptance of the License Agreement
(http://www.oracle.com/technetwork/licenses/standard-license-152015.html).

File: searchResult.ftl
Abstract: Template file to render Search Result Items
Version: 1.0
-->
<@compress single_line=true>
<div id="${widgetId}" class="SearchResult">
    <div id="${widgetId}_Alert" role="alert" class="ScreenReaderOnly"></div>
    <div id="${widgetId}_NoSearchResult" class="NoSearchResultMsg Hidden">${labelNoResults}</div>
    <div id="${widgetId}_Content" class="SearchResultContent">
        <#if searchText?? || resultList??>
            <#if resultList??>
                <div class="SearchResultTitle"><div class="SearchResultTitleAnswer">${labelResults}</div></div>
            <#elseif !isHideWhenNoResults>
                <div class="NoSearchResultMsg">${labelNoResults}</div>
            </#if>
            <#if resultList??>
                <#if resultList?size != 0 >
                    <table>
                    <#assign rowNum = 1>
                    <#foreach value in resultList>
                        <tr>
                            <td class="SearchResultAnswer">
                            <div class="Element1">
                                <span class="unlinkedAnswerIndent"></span>
                                <div class="answerTitle">
                                <#assign typeOfFile = value.fileType?lower_case?replace("-", "_") >
                                <#assign isIMDoc = false >
                                <#assign IM_DOC_ID = "" >
                                <#assign IM_DOC_INFO = {} >
                                <#if value.link??  &&  value.fileType=="CMS-XML">
                                    <#assign isIMDoc = true >
                                    <#assign IM_DOC_INFO=value.link?split(":")>
                                    <#assign IM_DOC_ID = IM_DOC_INFO[5] >
                                </#if>
                                <#if value.type == 'template' >
                                    <#assign typeOfFile = "intent" >
                                </#if>
                                <#assign fileClass = "SearchResultIcon File_" + typeOfFile >
                                <span class="${fileClass}"></span>
                                <a id="${value.answerId}" data-id="${value.docId}" href="${value.href}" target="${target}">${value.title}</a>
                                <#if showCaseLinks>
                                    </div>
                                    <#if typeOfFile == 'cms_xml' && showAddText>
                                        <button data-id="${value.globalAnswerId}" class="addText"></button>
                                    </#if>
                                    <#if showAddLink && showCaseLinks && isIMDoc>
                                        <#if value.globalAnswerId??>
                                            <button 
                                                id="${value.globalAnswerId}"
                                                data-id="${value.globalAnswerId}" 
                                                data-docid="${IM_DOC_ID}"
                                                class="addLink<#if linkedDocuments?seq_contains(IM_DOC_ID)> hidden</#if>"
                                            ></button>
                                        </#if>
                                    </#if>
                                </#if>
                            </div>
                            <#if value.textElements??>
                                <#if value.textElements?size != 0>
                                    <#assign excerptClass =  "" >
                                    <#if typeOfFile == "intent" >
                                        <#assign excerptClass = "Intent">
                                    </#if>
                                    <div class="SearchResultExcerpt ${excerptClass}" >
                                        <#if showCaseLinks>
                                            <span class="unlinkedAnswerIndent"></span>
                                            <div class="SearchExcerptText">
                                        </#if>
                                        <#foreach excerptSnippet in  value.textElements>
                                            <#foreach snippet in  excerptSnippet.snippets>
                                                <span class="SnippetLevel${snippet.level}">${snippet.text?replace("'", "\\'")}</span>
                                            </#foreach>
                                        </#foreach>
                                        <#if showCaseLinks>
                                            </div>
                                        </#if>
                                    </div>
                                </#if>    
                            </#if>
                            </td>
                        </tr>
                        <#assign rowNum = rowNum + 1 >
                    </#foreach>
                    </table>
                </#if>
            </#if>
        </#if>
    </div>
</div>
</@compress>