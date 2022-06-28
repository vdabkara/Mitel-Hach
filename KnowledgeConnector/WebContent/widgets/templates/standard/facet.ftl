<#--
 Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.

 The sample code in this document or accessed through this document is not certified or
 supported by Oracle. It is intended for educational or testing purposes only. Use of this
 sample code implies acceptance of the License Agreement
 (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).

 File: facet.ftl
 Abstract: Template file to render Facet Links
 Version: 1.0
 -->
<@compress single_line=true>
<div id="${widgetId}">
    <div id="${widgetId}" class="Facet">
        <div id="${widgetId}_Content" class="${widgetClass}">
            <div class="FacetsTitle">
                ${titleLabel}<span class="ClearContainer">[<a class="ClearFacets"><span class="ScreenReaderOnly">${clearFilterScreenReaderLabel}</span>${clearFilterLabel}</a>]</span>
            </div>
            <div class="FacetsList">
                <ul>
                    <#if facetList??> 
                        <#foreach facet in facetList>
                            <li>
                                <#if facet.desc??>
                                    ${facet.desc}<ul>
                                </#if>
                                <#macro subfacetgenerator facet>
                                    <#assign len = facet.children?size >
                                    <#assign displayFacetLength = maxSubFacetSize?number >
                                    <#if displayFacetLength gt 0 && len gt displayFacetLength >
                                        <#assign displayFacetLength = maxSubFacetSize>
                                    </#if>
                                    <#foreach currFacet in facet.children>
                                        <#if displayFacetLength?number gt 0 >
                                            <#if currFacet?? >
                                                <#if currFacet.children?size != 0 >
                                                    <li>
                                                        <a id="${currFacet.id}" class="FacetLink" href="javascript:void(0)">
                                                            <span class='ToggleExpandCollapse CategoryExplorerExpanded'></span>${currFacet.desc}
                                                        </a>
                                                    </li>
                                                     <ul nodeid="${currFacet.id}" class='FacetTreeIndent'>
                                                    <@subfacetgenerator facet=currFacet/> 
                                                <#else>
                                                    <li>
                                                        <#if currFacet.inEffect == 'true'>
                                                            <a id="${currFacet.id}" class="FacetLink ActiveFacet" href="javascript:void(0)">${currFacet.desc}</a>
                                                        <#else>
                                                            <a id="${currFacet.id}" class="FacetLink" href="javascript:void(0)">${currFacet.desc}</a>
                                                        </#if>
                                                    </li>
                                                </#if>
                                            </#if>
                                            <#assign displayFacetLength = displayFacetLength?number - 1>
                                        </#if>
                                    </#foreach>
                                    <#if  maxSubFacetSize?? && (facet.children?size gt maxSubFacetSize?number)>
                                        <li>
                                            <a id="F:${facet.id}" class="FacetLink" href="javascript:void(0)">${moreFilterLabel}
                                                <span class="ScreenReaderOnly">${moreScreenReaderLabel}</span>
                                            </a>
                                        </li>
                                    </#if>
                                </#macro>
                                <@subfacetgenerator facet=facet/>
                                <#if facet.desc??>
                                    </ul>
                                </#if>
                            </li>
                        </#foreach>
                    </#if>
                </ul>
            </div>
        </div>
    </div>
</div>
</@compress>