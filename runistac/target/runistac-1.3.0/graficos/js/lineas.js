function lineas(id, urlDatos) {
    var div = d3.select("#" + id);
    div.class = 'barras';

    var w = document.getElementById(id).clientWidth;
    div.html('<svg width="' + w + '" height="' + (window.innerWidth * 4 / 12 * 9 / 16) + '"></svg>');

    var svg = d3.select("#" + id + ">svg");
    var margin = {top: 40, right: 20, bottom: 30, left: 50};
    var width = +svg.attr("width") - margin.left - margin.right;
    var height = +svg.attr("height") - margin.top - margin.bottom;
    var g = svg.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    //var x = d3.scaleTime().rangeRound([0, width]);
    var x = d3.scaleBand().rangeRound([0, width]).padding(0.1)
    var y = d3.scaleLinear().rangeRound([height, 0]);

    var line = d3.line()
            .x(function (d) {
                return x(d.clave);
            })
            .y(function (d) {
                return y(d.valor);
            });

    d3.json(urlDatos, function (error, data) {
        if (error)
            throw error;

        x.domain(data.map(function (d) {
            return d.clave;
        }));
        y.domain([d3.min(data, function (d) {
                return d.valor;
            }) - 100, d3.max(data, function (d) {
                return d.valor;
            }) + 100]);

        g.append("g")
                .attr("transform", "translate(0," + height + ")")
                .call(d3.axisBottom(x))
                .select(".domain");

        g.append("g")
                .call(d3.axisLeft(y))
                .append("text")
                .attr("fill", "#000")
                .attr("transform", "rotate(-90)")
                .attr("y", 6)
                .attr("dy", "0.71em")
                .attr("text-anchor", "end");

        g.append("path")
                .datum(data)
                .attr("fill", "none")
                .attr("stroke", "steelblue")
                .attr("transform", "translate(" + (x.bandwidth() / 2) + ", 0)")
                .attr("stroke-linejoin", "round")
                .attr("stroke-linecap", "round")
                .attr("stroke-width", 1.5)
                .attr("d", line);

        g.append('g')
                .classed('labels-group', true)
                .selectAll('text')
                .data(data)
                .enter()
                .append('circle')
                .classed('label', true)
                .attr("class", "circle-line")
                .attr("r", 5)
                .attr("fill", "steelblue")
                .attr("transform", "translate(" + (x.bandwidth() / 2) + ", 0)")
                .attr("cx", function (d) {
                    return x(d.clave);
                })
                .attr("cy", function (d) {
                    return y(d.valor);
                })
                .on('mouseover', function (d) {
                    $('#' + id + ' #tooltip-' + d.id).attr('class', 'tooltip2');
                })
                .on('mouseout', function (d) {
                    $('#' + id + ' #tooltip-' + d.id).attr('class', 'tooltip1');
                });

        //tooltip
        var tooltip = g.append('g')
                .classed('labels-group', true)
                .selectAll('text')
                .data(data)
                .enter()
                .append('g')
                .attr('id', function (d) {
                    return 'tooltip-' + d.id;
                })
                .attr("class", "tooltip1");

        tooltip.append('rect')
                .attr("width", "100")
                .attr("height", "40")
                .attr("rx", "5")
                .attr("ry", "5")
                .attr("id", function (d) {
                    return "punto" + d.clave;
                })
                .attr("transform", "translate(" + (x.bandwidth() / 2) + ", 0)")
                .attr("x", function (d) {
                    return x(d.clave) - 50;
                })
                .attr("y", function (d) {
                    return y(d.valor) - 60;
                });
        tooltip.append('polygon')
                .attr("style", "fill:steelblue;stroke:steelblue;stroke-width:1")
                .attr("id", function (d) {
                    return "puntoconector" + d.clave;
                })
                .attr("transform", "translate(" + (x.bandwidth() / 2) + ", 0)")
                .attr("points", function (d) {
                    var p1 = '' + x(d.clave) + ',' + (y(d.valor) - 10);
                    var p2 = '' + (x(d.clave) - 10) + ',' + (y(d.valor) - 20);
                    var p3 = '' + (x(d.clave) + 10) + ',' + (y(d.valor) - 20);
                    return '' + p1 + ' ' + p2 + ' ' + p3;
                });
        tooltip.append('text')
                .attr("class", "tooltip-info")
                .attr("text-anchor", "middle")
                .attr("transform", "translate(" + (x.bandwidth() / 2) + ", 15)")
                .attr("x", function (d) {
                    return x(d.clave);
                })
                .attr("y", function (d) {
                    return y(d.valor) - 40;
                })
                .text(function (d) {
                    return d.valor;
                });

        tooltip.append('text')
                .attr("class", "tooltip-title")
                .attr("text-anchor", "middle")
                .attr("transform", "translate(" + (x.bandwidth() / 2) + ", 15)")
                .attr("x", function (d) {
                    return x(d.clave);
                })
                .attr("y", function (d) {
                    return y(d.valor) - 60;
                })
                .text(function (d) {
                    return d.clave;
                });
    });
}

function lineasDatos(id,datos) {
    var div = d3.select("#" + id);
    div.class = 'barras';

    var w = document.getElementById(id).clientWidth;
    div.html('<svg width="' + w + '" height="' + (window.innerWidth * 4 / 12 * 9 / 16) + '"></svg>');

    var svg = d3.select("#" + id + ">svg");
    var margin = {top: 40, right: 20, bottom: 30, left: 50};
    var width = +svg.attr("width") - margin.left - margin.right;
    var height = +svg.attr("height") - margin.top - margin.bottom;
    var g = svg.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    //var x = d3.scaleTime().rangeRound([0, width]);
    var x = d3.scaleBand().rangeRound([0, width]).padding(0.1)
    var y = d3.scaleLinear().rangeRound([height, 0]);

    var line = d3.line()
            .x(function (d) {
                return x(d.clave);
            })
            .y(function (d) {
                return y(d.valor);
            });
    x.domain(data.map(function (d) {
        return d.clave;
    }));
    y.domain([d3.min(data, function (d) {
            return d.valor;
        }) - 100, d3.max(data, function (d) {
            return d.valor;
        }) + 100]);

    g.append("g")
            .attr("transform", "translate(0," + height + ")")
            .call(d3.axisBottom(x))
            .select(".domain");

    g.append("g")
            .call(d3.axisLeft(y))
            .append("text")
            .attr("fill", "#000")
            .attr("transform", "rotate(-90)")
            .attr("y", 6)
            .attr("dy", "0.71em")
            .attr("text-anchor", "end");

    g.append("path")
            .datum(data)
            .attr("fill", "none")
            .attr("stroke", "steelblue")
            .attr("transform", "translate(" + (x.bandwidth() / 2) + ", 0)")
            .attr("stroke-linejoin", "round")
            .attr("stroke-linecap", "round")
            .attr("stroke-width", 1.5)
            .attr("d", line);

    g.append('g')
            .classed('labels-group', true)
            .selectAll('text')
            .data(data)
            .enter()
            .append('circle')
            .classed('label', true)
            .attr("class", "circle-line")
            .attr("r", 5)
            .attr("fill", "steelblue")
            .attr("transform", "translate(" + (x.bandwidth() / 2) + ", 0)")
            .attr("cx", function (d) {
                return x(d.clave);
            })
            .attr("cy", function (d) {
                return y(d.valor);
            })
            .on('mouseover', function (d) {
                $('#' + id + ' #tooltip-' + d.id).attr('class', 'tooltip2');
            })
            .on('mouseout', function (d) {
                $('#' + id + ' #tooltip-' + d.id).attr('class', 'tooltip1');
            });

    //tooltip
    var tooltip = g.append('g')
            .classed('labels-group', true)
            .selectAll('text')
            .data(data)
            .enter()
            .append('g')
            .attr('id', function (d) {
                return 'tooltip-' + d.id;
            })
            .attr("class", "tooltip1");

    tooltip.append('rect')
            .attr("width", "100")
            .attr("height", "40")
            .attr("rx", "5")
            .attr("ry", "5")
            .attr("id", function (d) {
                return "punto" + d.clave;
            })
            .attr("transform", "translate(" + (x.bandwidth() / 2) + ", 0)")
            .attr("x", function (d) {
                return x(d.clave) - 50;
            })
            .attr("y", function (d) {
                return y(d.valor) - 60;
            });
    tooltip.append('polygon')
            .attr("style", "fill:steelblue;stroke:steelblue;stroke-width:1")
            .attr("id", function (d) {
                return "puntoconector" + d.clave;
            })
            .attr("transform", "translate(" + (x.bandwidth() / 2) + ", 0)")
            .attr("points", function (d) {
                var p1 = '' + x(d.clave) + ',' + (y(d.valor) - 10);
                var p2 = '' + (x(d.clave) - 10) + ',' + (y(d.valor) - 20);
                var p3 = '' + (x(d.clave) + 10) + ',' + (y(d.valor) - 20);
                return '' + p1 + ' ' + p2 + ' ' + p3;
            });
    tooltip.append('text')
            .attr("class", "tooltip-info")
            .attr("text-anchor", "middle")
            .attr("transform", "translate(" + (x.bandwidth() / 2) + ", 15)")
            .attr("x", function (d) {
                return x(d.clave);
            })
            .attr("y", function (d) {
                return y(d.valor) - 40;
            })
            .text(function (d) {
                return d.valor;
            });

    tooltip.append('text')
            .attr("class", "tooltip-title")
            .attr("text-anchor", "middle")
            .attr("transform", "translate(" + (x.bandwidth() / 2) + ", 15)")
            .attr("x", function (d) {
                return x(d.clave);
            })
            .attr("y", function (d) {
                return y(d.valor) - 60;
            })
            .text(function (d) {
                return d.clave;
            });

}