<#--
 Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.

 The sample code in this document or accessed through this document is not certified or
 supported by Oracle. It is intended for educational or testing purposes only. Use of this
 sample code implies acceptance of the License Agreement
 (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).

 File: answerError.ftl
 Abstract: Template file to display error message while viewing answers
 Version: 1.0
-->
<@compress single_line=true>
<div role="main" class="rn_MainColumn">
	<a id="rn_MainContent"></a>
	<div class="rn_Hero">
    	<div class="rn_Container">
        	<h1>${error}</h1>
    	</div>
	</div>
	<div class="rn_PageContent rn_ErrorPage rn_Container">
    	<p>${message}</p>
	</div>
</div>
</@compress>