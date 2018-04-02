var color = d3.scaleOrdinal(["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);

function pie(id, url) {
    var div = d3.select("#" + id);
    div.class = 'barras';

    var w = document.getElementById(id).clientWidth;
    div.html('<svg width="' + w + '" height="' + (window.innerWidth * 4 / 12 * 9 / 16) + '"></svg>');

    var svg = d3.select("#" + id + ">svg");
    var width = +svg.attr("width");
    var height = +svg.attr("height");
    var radius = (Math.min(width, height) - 40) / 2;
    var g = svg.append("g").attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

    var pie = d3.pie()
            .sort(null)
            .value(function (d) {
                return d.valor;
            });

    var path = d3.arc()
            .outerRadius(radius - 10)
            .innerRadius(0);



    //tooltip
    var tooltip = svg.append('g')
            .classed('labels-group', true)
            .attr('id', 'tooltip-' + id)
            .attr("class", "tooltip1");

    tooltip.append('rect')
            .attr("width", "100")
            .attr("height", "60")
            .attr("rx", "5")
            .attr("ry", "5")
            .attr("x", 0)
            .attr("y", 0);

    tooltip.append('polygon')
            .attr("style", "fill:steelblue;stroke:steelblue;stroke-width:1")
            .attr("points", function (d) {
                var p1 = '40,60';
                var p2 = '60,60';
                var p3 = '50,70';
                return '' + p1 + ' ' + p2 + ' ' + p3;
            });

    tooltip.append('text')
            .attr("class", "tooltip-title")
            .attr("text-anchor", "middle")
            .attr("x", 50)
            .attr("y", 15)
            .text("b");
    tooltip.append('text')
            .attr("class", "tooltip-info")
            .attr("text-anchor", "middle")
            .attr("x", 50)
            .attr("y", 35)
            .text("a");
    tooltip.append('text')
            .attr("class", "tooltip-porcentaje")
            .attr("text-anchor", "middle")
            .attr("x", 50)
            .attr("y", 55)
            .text("c");

    d3.json(url, function (error, data) {
        if (error)
            throw error;

        var total = d3.sum(data.map(function (v) {
            return v.valor;
        }));


        var arc = g.selectAll(".arc")
                .data(pie(data))
                .enter().append("g")
                .attr("class", "arc");

        arc.append("path")
                .attr("d", path)
                .attr("fill", function (d) {
                    return color(d.data.clave);
                })
                .on('mouseover', function (d) {
                    d3.select('#tooltip-' + id).attr('class', 'tooltip2');
                    d3.select('#tooltip-' + id + ' .tooltip-title').html(d.data.clave);
                    d3.select('#tooltip-' + id + ' .tooltip-info').html(d.data.valor);
                    d3.select('#tooltip-' + id + ' .tooltip-porcentaje').html(d3.format(".2%")(d.data.valor / total));
                })
                .on('mouseout', function (d) {
                    $('#tooltip-' + id).attr('class', 'tooltip1');
                })
                .on('mousemove', function (d) {
                    var x = d3.mouse(this)[0] + width / 2 - 50;
                    var y = d3.mouse(this)[1] + height / 2 - 80;
                    d3.select('#tooltip-' + id).attr("transform", "translate(" + x + ", " + y + ")");


                });

        legend(id, data);

    });
}

function legend(id, lD) {
    // create table for legend.
    var legend = d3.select("#" + id).append("table").attr('class', 'legend');

    // create one row per segment.
    var tr = legend.append("tbody").selectAll("tr").data(lD).enter().append("tr");

    // create the first column for each segment.
    tr.append("td").append("svg").attr("width", '16').attr("height", '16').append("rect")
            .attr("width", '16').attr("height", '16')
            .attr("fill", function (d) {
                return color(d.clave);
            });

    // create the second column for each segment.
    tr.append("td").text(function (d) {
        return d.clave;
    });
    tr.append("td").text(function (d) {
        return d.valor;
    });

    tr.append("td").attr("class", 'legendPerc')
            .text(function (d) {
                return getLegend(d, lD);
            });

    function getLegend(d, aD) { // Utility function to compute percentage.
        return d3.format(".2%")(d.valor / d3.sum(aD.map(function (v) {
            return v.valor;
        })));
    }
}


function pieDatos(id, data) {
    var div = d3.select("#" + id);
    div.class = 'barras';

    var w = document.getElementById(id).clientWidth;
    div.html('<svg width="' + w + '" height="' + (window.innerWidth * 4 / 12 * 9 / 16) + '"></svg>');

    var svg = d3.select("#" + id + ">svg");
    var width = +svg.attr("width");
    var height = +svg.attr("height");
    var radius = (Math.min(width, height) - 40) / 2;
    var g = svg.append("g").attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

    var pie = d3.pie()
            .sort(null)
            .value(function (d) {
                return d.valor;
            });

    var path = d3.arc()
            .outerRadius(radius - 10)
            .innerRadius(0);



    //tooltip
    var tooltip = svg.append('g')
            .classed('labels-group', true)
            .attr('id', 'tooltip-' + id)
            .attr("class", "tooltip1");

    tooltip.append('rect')
            .attr("width", "100")
            .attr("height", "60")
            .attr("rx", "5")
            .attr("ry", "5")
            .attr("x", 0)
            .attr("y", 0);

    tooltip.append('polygon')
            .attr("style", "fill:steelblue;stroke:steelblue;stroke-width:1")
            .attr("points", function (d) {
                var p1 = '40,60';
                var p2 = '60,60';
                var p3 = '50,70';
                return '' + p1 + ' ' + p2 + ' ' + p3;
            });

    tooltip.append('text')
            .attr("class", "tooltip-title")
            .attr("text-anchor", "middle")
            .attr("x", 50)
            .attr("y", 15)
            .text("b");
    tooltip.append('text')
            .attr("class", "tooltip-info")
            .attr("text-anchor", "middle")
            .attr("x", 50)
            .attr("y", 35)
            .text("a");
    tooltip.append('text')
            .attr("class", "tooltip-porcentaje")
            .attr("text-anchor", "middle")
            .attr("x", 50)
            .attr("y", 55)
            .text("c");


    var total = 0;
    var data1 = [];
    
    Object.keys(data).forEach(function (v) {
        total += data[v].cantidad;
        data1.push({clave: v, valor: data[v].cantidad});
    });


    var arc = g.selectAll(".arc")
            .data(pie(data1))
            .enter().append("g")
            .attr("class", "arc");

    arc.append("path")
            .attr("d", path)
            .attr("fill", function (d) {
                return color(d.data.clave);
            })
            .on('mouseover', function (d) {
                d3.select('#tooltip-' + id).attr('class', 'tooltip2');
                $('#tooltip-' + id + ' .tooltip-title').empty().append(d.data.clave);
                $('#tooltip-' + id + ' .tooltip-info').empty().append(d.data.valor);
                $('#tooltip-' + id + ' .tooltip-porcentaje').empty().append(d3.format(".2%")(d.data.valor / total));
            })
            .on('mouseout', function (d) {
                $('#tooltip-' + id).attr('class', 'tooltip1');
            })
            .on('mousemove', function (d) {
                var x = d3.mouse(this)[0] + width / 2 - 50;
                var y = d3.mouse(this)[1] + height / 2 - 80;
                d3.select('#tooltip-' + id).attr("transform", "translate(" + x + ", " + y + ")");


            });

    legend(id, data1);
}