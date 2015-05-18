require(['jquery','highcharts','scroll','bootstrap','carousel'],function($){
  $("#isShow").css("display",'none');
  $("#menu").css("display",'block');

  $("#navShow").mouseover(function(){
    $("#menu").css("display","block");
  });
  $("#navShow").mouseout(function(){
    $("#menu").css("display","block");
  });

  //轮播图片  start
  $('.carousel').carousel({
    interval: 5000
  })

  //setInterval('autoScroll(".list_lh")',2000);
  $(".list_lh").myScroll({
    speed:80,
    rowHeight:38
  });

  /* Chart Test Data
  //Data structure: minMaxLimited = [BSPI最小值，最大值，API8最小值，最大值，TC1505最小值，最大值];
  minMaxLimited = [505, 530, 57, 65, 460, 470];

  // Chart 1 BSPI指数    12条数据 元/瓶
  var BSPIxAxisData1 = ["11-19", "11-26", "12-03", "12-10", "1217", "1224", "1231", "0107", "0114", "0121", "0128", "0204"];
  var BSPIseriesData1 = [513, 513, 521, 526, 526, 525, 525, 520, 519, 518, 511, 510];
  commonChart('chartContainer1', BSPIxAxisData1, BSPIseriesData1, 'BSPI指数', "元/瓶", "￥", 505, 530);
  commonChart('chartContainer1', BSPIxAxisData1, BSPIseriesData1, 'BSPI指数', "元/瓶", "￥", minMaxLimited[0], minMaxLimited[1]);

  // Chart 2 API8指数    12条数据 美元/瓶
  var API8xAxisData2 = ["1107", "1114", "1121", "1128", "1205", "1212", "1219", "1226", "0102", "0109", "0116", "0123", "0130"];
  var API8seriesData2 = [64.18, 64.14, 64.08, 63.83, 63.91, 62.63, 62.00, 61.49, 60.69, 59.99, 59.84, 59.74, 59.63];
  commonChart('chartContainer2', API8xAxisData2, API8seriesData2, 'API8指数', "美元/瓶", "$", 57, 65);
  commonChart('chartContainer2', API8xAxisData2, API8seriesData2, 'API8指数', "美元/瓶", "$", minMaxLimited[2], minMaxLimited[3]);

  // Chart 3 TC1505指数    10条数据 元/瓶
  var TC1505xAxisData3 = ["0126", "0127", "0128", "0129", "0130", "0202", "0203", "0204", "0205", "0206" ];
  var TC1505seriesData3 = [466.4, 467.2, 467.4, 468.8, 467.2, 468, 467.4, 464.2, 462.4, 464];
  commonChart('chartContainer3', TC1505xAxisData3, TC1505seriesData3, 'TC1505指数', "元/瓶", "￥", 460, 470);
  commonChart('chartContainer3', TC1505xAxisData3, TC1505seriesData3, 'TC1505指数', "元/瓶", "￥", minMaxLimited[4], minMaxLimited[5]);
  */

  // Chart 1 BSPI指数    12条数据 元/瓶
  commonChart('chartContainer1', BSPIxAxisData1, BSPIseriesData1, 'BSPI指数', "元/瓶", "￥", minMaxLimited[0], minMaxLimited[1]);
  // Chart 2 API8指数    12条数据 美元/瓶
  commonChart('chartContainer2', API8xAxisData2, API8seriesData2, 'API8指数', "美元/瓶", "$", minMaxLimited[2], minMaxLimited[3]);
  // Chart 3 TC1505指数    10条数据 元/瓶
  commonChart('chartContainer3', TC1505xAxisData3, TC1505seriesData3, 'TC1505指数', "元/瓶", "￥", minMaxLimited[4], minMaxLimited[5]);

  /**
   *  Common Chart Function
   * @param id          页面绘图区域的id
   * @param xAxisData   X轴数据
   * @param seriesData  Y轴数据
   * @param seriesName
   * @param unit        数据单位
   * @param yAxisLabels Y轴标签（$/￥）
   * @param yAxisMin    Y轴最小值
   * @param yAxisMax    Y轴最大值
   */
  function commonChart(id, xAxisData, seriesData, seriesName, unit, yAxisLabels, yAxisMin, yAxisMax){
    $('#'+ id).highcharts({
      chart: {
        type: 'area'
      },
      title: {
        text: ""
      },
      credits: {
        enabled: false  // 是否显示版权信息
      },
      legend: {
        enabled: false
      },

      labels: {
        style:{
          color: "#ff0000"
        },
        items: {
          html: "元/瓶",
          style: {
            left: '50px',
            top: '100px'
          }
        }
      },

      xAxis: {
        gridLineColor:  '#EFEFEF',
        gridLineWidth:  1,
        categories: xAxisData,
        tickmarkPlacement: 'on', // 刻度线位于在类别名称的中心
        //startOnTick: false,
        //endOnTick: false,
        labels: {
          rotation: 320
        }
      },

      yAxis: {
        min: yAxisMin,
        max: yAxisMax,
        gridLineColor:  '#EFEFEF',
        gridLineWidth:  1,
        //startOnTick:  false,
        //tickInterval: 25,
        tickPixelInterval: 10,
        minRange: 1,
        title: {
          text: null
        },
        labels: {
          formatter: function() {
            return yAxisLabels + this.value;
          }
        }
      },

      plotOptions: {
        area: {
          lineColor: '#FF5441',
          lineWidth: 2,
          fillColor: 'rgba(255,221,217,0.5)', // 最后一个参数是透明度
          //fillOpacity: 0.4,  //透明度只有用默认颜色才生效
          marker: {
            lineWidth: 1,
            lineColor: '#fff',
            fillColor: '#FF9100'
          }
        }
      },

      series: [{
        name: seriesName,
        data: seriesData
      }],
      tooltip: {
        formatter: function () {
          var s = '<b>日期：' + this.x + '</b>';
          $.each(this.points, function () {
            s += '<br/>' + this.series.name + ': ' +
            this.y + unit;
          });
          return s;
        },
        shared: true
      }
    });
  }
  /* common chart end */
})


function autoScroll(obj){
  $(obj).find("dl").animate({
    marginTop : "-86px"
  },800,function(){
    $(this).css({marginTop : "0px"}).find("dt:first").appendTo(this);
  })
}
