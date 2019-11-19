$(document).ready(function () {
    //访问地图
    var myChart = echarts.init(document.getElementById('main'));
    var option={
        title: {
            text: "地区访问展示",
            left: "center",
            x: '20%',
            textStyle: {
                fontSize: '18',
                fontWeight: 'bold'
            }
        },
        tooltip: {},
        visualMap: {
            min: 0,
            max: 100,
            left: 'left',
            top: 'bottom',
            text: ['High','Low'],
            seriesIndex: [1],
            inRange: {
                color: ['#e0ffff', '#006edd']
            },
            calculable : true
        },
        geo: {
            map: 'china',
            roam: true,
            label: {
                normal: {
                    show: true,
                    textStyle: {
                        color: 'rgba(0,0,0,0.4)'
                    }
                }
            },
            itemStyle: {
                normal:{
                    borderColor: 'rgba(0, 0, 0, 0.2)'
                },
                emphasis:{
                    areaColor: null,
                    shadowOffsetX: 0,
                    shadowOffsetY: 0,
                    shadowBlur: 20,
                    borderWidth: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        },
        series : [
            {
                type: 'scatter',
                coordinateSystem: 'geo',
                data: [],
                symbolSize: 20,
                symbol: 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z',
                symbolRotate: 35,
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#F06C00'
                    }
                }
            },
            {
                name: 'ip访问量',
                type: 'map',
                geoIndex: 0,
//                 tooltip: {show: false},
                data:[]
            }
        ]
    };
    myChart.setOption(option);
    // myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
    var names=[];    //类别数组（实际用来盛放X轴坐标值）
    var nums=[];    //销量数组（实际用来盛放Y坐标值）

    $.ajax({
        url: "getRegion",
        type: "POST",
        dataType: "json",
        cache: false,
        success: function (data) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            // var map=data.data;

            for(var i = 0; i < data.length; i++){
                var obj = new Object();
                obj.name = data[i].name;
                obj.value = data[i].value;
                names.push(obj.name);
                nums.push(obj); //挨个取出销量并填入销量数组
            }
            console.log(nums);
            myChart.hideLoading();    //隐藏加载动画
            myChart.setOption({        //加载数据图表
                series: [{
                    name: 'ip访问量',
                    data: nums
                }]
            });
        },
        error: function (error) {
            console.log('操作失败！请重试！');
            console.log(error);
            myChart.hideLoading();
        }
    });


//    访问浏览器饼图
    var myChart1 = echarts.init(document.getElementById('main1'));
    // var option1 = {
    //     title: {
    //         text: "浏览器",
    //         left: "center",
    //         x: '20%',
    //         textStyle: {
    //             fontSize: '16',
    //             fontWeight: 'normal'
    //         }
    //     },
    //     legend: {
    //         bottom: 0,
    //         data: ["Chrome","Firefox","QQ浏览器","搜狗高速浏览器","Internet Explorer 11","360安全浏览器","Opera","Edge"]
    //     },
    //     series: [{
    //         type: "pie",
    //         name: "服务对象",
    //         label: {
    //             normal: {
    //                 position: "outside",
    //                 formatter: "{d}%",
    //                 textStyle: {
    //                     fontSize: '16',
    //                 }
    //             }
    //         },
    //         data: [{
    //             value: 20,
    //             name: "Chrome"
    //         }, {
    //             value: 135,
    //             name: "Firefox"
    //         }, {
    //             value: 105,
    //             name: "QQ浏览器"
    //         }, {
    //             value: 130,
    //             name: "搜狗高速浏览器"
    //         }, {
    //             value: 55,
    //             name: "Internet Explorer 11"
    //         }, {
    //             value: 40,
    //             name: "360安全浏览器"
    //         }, {
    //             value: 40,
    //             name: "Opera"
    //         }, {
    //             value: 70,
    //             name: "Edge"
    //         }],
    //         center: ["50%", "50%"],
    //         radius: [0, "59%"]
    //     }],
    //     color: ['#5eade5', '#f3ae32', '#BA55D3', '#FF00FF', '#FF1493', '#DC143C', '#D8BFD8', '#4B0082']
    // };
    option1 = {
        title : {
            text: '浏览器',
            // subtext: '纯属虚构',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 200,
            bottom: 20,
            data: [],

            // selected: data.selected
        },
        series : [
            {
                name: '浏览器：',
                type: 'pie',
                radius : '55%',
                center: ['40%', '50%'],
                data: [],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    myChart1.setOption(option1);

    $.ajax({
        url: "getBrowser",
        type: "POST",
        dataType: "json",
        cache: false,
        success: function (data) {
            console.log(data);
            var serviceData = [];
            var name = [];
            var value = [];
            for (var i = 0; i < data.length; i++) {
                var obj = new Object();
                obj.name = data[i].name;
                obj.value = data[i].value;
                serviceData[i] = obj;
                name[i] = obj.name;
                value[i] = obj.value;
            }

            myChart1.setOption({
                legend: {
                    data: name
                },
                series: [{
                    data: serviceData
                }]
            });
        },
        error: function (error) {
            console.log('操作失败！请重试！');
            console.log(error);
        }
    });
});
