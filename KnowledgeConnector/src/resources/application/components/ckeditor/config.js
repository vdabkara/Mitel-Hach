/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
    
	// turn Replace actual contents checkbox off
	config.templates_replaceContent = false;	
	
	 // Oracle Knowledge "Default"
    config.toolbar_Default = 
    [
        ['Source','-','NewPage','Preview','-','Templates'], // TODO Research 'DocProps'
        ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print'],
        ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
        ['Form','Checkbox','Radio','TextField','Textarea','Select','Button','ImageButton','HiddenField'],
        '/',
        ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
        ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
        ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
        ['Link','Unlink','Anchor'],
       // ['Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
        ['Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
        '/',
        ['Styles','Format','Font','FontSize'],
        ['TextColor','BGColor'],
        ['Maximize','ShowBlocks','-','About']
    ] ;

   // Oracle Knowledge "MEDIUM"
    config.toolbar_MEDIUM =
    [
        ['Source','-','Preview'],
        ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
        ['Cut','Copy','Paste','PasteText','PasteFromWord'],
        ['Find','Replace','-','SelectAll'],
        ['NumberedList','BulletedList','-','Outdent','Indent'],
        ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
        ['Link','Unlink','Anchor'],
       // ['Image','Table','HorizontalRule'],
        ['Table','HorizontalRule'],
        ['TextColor','BGColor','-','About']
    ] ;

    // Oracle Knowledge "BASIC"
    config.toolbar_BASIC =
    [
        ['Source','-','Bold','Italic','Underline','Strike','-','Subscript','Superscript','-','Outdent','Indent','-','Link','Unlink','-','About']
    ] ;    
	config.pasteFromWordRemoveFontStyles = false; 
	config.pasteFromWordRemoveStyles = false;    

};
