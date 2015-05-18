require.config({
  baseUrl: "/scripts",
  shim: {
    jquery_validation: {
      deps: [
        "jquery"
      ]
    },
    "jquery-form": {
      deps: [
        "jquery"
      ]
    },
    "jquery.fileupload": {
      deps: [
        "jquery"
      ]
    },
    "jquery.iframe-transport": {
      deps: [
        "jquery"
      ]
    },
    bootstrap: {
      deps: [
        "jquery"
      ]
    },
    pagination: {
      deps: [
        "jquery"
      ]
    },
    "bootstrap3-datetimepicker": {
      deps: [
        "bootstrap"
      ]
    },
    popover: {
      deps: [
        "bootstrap"
      ]
    },
    tooltip: {
      deps: [
        "bootstrap"
      ]
    },
    "bootstrap-datetimepicker": {
      deps: [
        "bootstrap"
      ]
    },
    CN: {
      deps: [
        "bootstrap3-datetimepicker"
      ]
    },
    highcharts: {
      deps: [
        "jquery"
      ]
    },
    scroll: {
      deps: [
        "jquery"
      ]
    },
    carousel: {
      deps: [
        "jquery"
      ]
    }
  },
  packages: [

  ],
  paths: {
    bootstrap: "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap",
    affix: "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/affix",
    alert: "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/alert",
    button: "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/button",
    carousel: "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/carousel",
    collapse: "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/collapse",
    dropdown: "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/dropdown",
    tab: "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/tab",
    transition: "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/transition",
    scrollspy: "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/scrollspy",
    modal: "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/modal",
    tooltip: "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/tooltip",
    popover: "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/popover",
    "bootstrap3-datetimepicker": "../bower_components/bootstrap3-datetimepicker/build/js/bootstrap-datetimepicker.min",
    "jquery.postmessage-transport": "../bower_components/jquery-file-upload/js/cors/jquery.postmessage-transport",
    "jquery.xdr-transport": "../bower_components/jquery-file-upload/js/cors/jquery.xdr-transport",
    "jquery.ui.widget": "../bower_components/jquery-file-upload/js/vendor/jquery.ui.widget",
    "jquery.fileupload": "../bower_components/jquery-file-upload/js/jquery.fileupload",
    "jquery.fileupload-process": "../bower_components/jquery-file-upload/js/jquery.fileupload-process",
    "jquery.fileupload-validate": "../bower_components/jquery-file-upload/js/jquery.fileupload-validate",
    "jquery.fileupload-image": "../bower_components/jquery-file-upload/js/jquery.fileupload-image",
    "jquery.fileupload-audio": "../bower_components/jquery-file-upload/js/jquery.fileupload-audio",
    "jquery.fileupload-video": "../bower_components/jquery-file-upload/js/jquery.fileupload-video",
    "jquery.fileupload-ui": "../bower_components/jquery-file-upload/js/jquery.fileupload-ui",
    "jquery.fileupload-jquery-ui": "../bower_components/jquery-file-upload/js/jquery.fileupload-jquery-ui",
    "jquery.fileupload-angular": "../bower_components/jquery-file-upload/js/jquery.fileupload-angular",
    "jquery.iframe-transport": "../bower_components/jquery-file-upload/js/jquery.iframe-transport",
    "jquery-form": "../bower_components/jquery-form/jquery.form",
    "jquery.validation": "../bower_components/jquery.validation/dist/jquery.validate",
    requirejs: "../bower_components/requirejs/require",
    moment: "../bower_components/moment/moment",
    CN: "../bower_components/moment/locale/zh-cn",
    scroll: "scroll",
    "bootstrap-sass-official": "../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap",
    highcharts: "../bower_components/highcharts/highcharts",
    "highcharts-more": "../bower_components/highcharts/highcharts-more",
    exporting: "../bower_components/highcharts/modules/exporting",
    "bootstrap-datetimepicker": "../bower_components/bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min",
    jquery: "../bower_components/jquery/dist/jquery",
    html5shiv: "../bower_components/html5shiv/dist/html5shiv",
    respond: "../bower_components/respond/dest/respond.src"
  }
});

require(['jquery', 'common'],function($, common){
    $.ajaxSetup({
      global: true,
      type: "POST",
      dataType:"json",
      beforeSend: function(jqXHR, settings){
        jqXHR.$setting=settings;
      },
      error:function(jqXHR, textStatus, exception){
        //console.log(jqXHR);
        var XHRstatus = jqXHR.status;
        var message  =  jqXHR.responseText;
        var errorStatus = [400, 403, 404, 500,409];
        if(XHRstatus==401){
          common.login(jqXHR.$setting);
        } else if($.inArray(XHRstatus, errorStatus) >=0) {
          common.showServerError(XHRstatus,message);  //handle errors in errorStatus
        }else{

        }
      }
    });
})



