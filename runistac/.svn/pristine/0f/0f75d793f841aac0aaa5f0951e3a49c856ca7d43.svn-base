function barras(id, urlDatos) {
    var div = d3.select("#" + id);
    div.class = 'barras';
    var w = document.getElementById(id).clientWidth;
    div.html('<select class="selectpicker" multiple id="select' + id + '"> </select><svg width="' + w + '" height="' + (window.innerWidth * 4 / 12 * 9 / 16 + 20) + '"></svg>');
    $('#' + "select" + id).selectpicker();
    var svg = d3.select("#" + id + ">svg");
    var margin = {top: 70, right: 60, bottom: 0, left: 60};
    var width = +w - margin.left - margin.right;
    var height = +(window.innerWidth * 4 / 12 * 9 / 16) - margin.top - margin.bottom;

    var x = d3.scaleBand().rangeRound([0, width]).padding(0.1);
    var y = d3.scaleLinear().rangeRound([height, 0]);



    var g = svg.append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    //tooltip
    var tooltip = svg.append('g')
            .classed('labels-group', true)
            .attr('id', 'tooltip-' + id)
            .attr("class", "tooltip1");

    tooltip.append('rect')
            .attr("width", "150")
            .attr("height", "50")
            .attr("rx", "5")
            .attr("ry", "5")
            .attr("x", -25)
            .attr("y", 10);

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
            .attr("y", 25)
            .text("b");
    tooltip.append('text')
            .attr("class", "tooltip-info")
            .attr("text-anchor", "middle")
            .attr("x", 50)
            .attr("y", 40)
            .text("a");
    tooltip.append('text')
            .attr("class", "tooltip-porcentaje")
            .attr("text-anchor", "middle")
            .attr("x", 50)
            .attr("y", 55)
            .text("c");

    d3.json(urlDatos, function (error, data) {
        if (error)
            throw error;

        var total = d3.sum(data.map(function (v) {
            return v.valor;
        }));

        var array = [];
        var select = document.getElementById("select" + id);
        var option = document.createElement("option");
        option.value = "Seleccionar Todos";
        option.textContent = "Seleccionar Todos";
        select.appendChild(option);
        array.push("Seleccionar Todos");
        data.map(function (d) {
            var option = document.createElement("option");
            option.value = d.clave;
            option.textContent = d.clave;
            select.appendChild(option);
            array.push(d.clave);

        });

        $('#' + "select" + id).val(array);
        $('#' + "select" + id).selectpicker('refresh');
        x.domain(data.map(function (d) {
            return d.valor;
        }));
        y.domain([0, d3.max(data, function (d) {
                return d.valor;
            })]);

        g.append("g")
                .attr("class", "axis axis--x")
                .attr("transform", "translate(0," + height + ")")
                .call(d3.axisBottom(x));

        g.append("g")
                .attr("class", "axis axis--y")
                .call(d3.axisLeft(y))
                .append("text")
                .attr("transform", "rotate(-90)")
                .attr("y", 6)
                .attr("dy", "0.71em")
                .attr("text-anchor", "end")
                .text("Frequency");


        g.selectAll(".bar")
                .data(data)
                .enter().append("rect")
                .attr("class", "bar")
                .attr("x", function (d) {
                    return x(d.valor);
                })
                .attr("y", function (d) {
                    return y(d.valor);
                })
                .attr("width", x.bandwidth())
                .attr("height", function (d) {
                    return height - y(d.valor);
                })
                .on('mouseover', function (d) {
                    d3.select('#tooltip-' + id).attr('class', 'tooltip2');
                    d3.select('#tooltip-' + id + ' .tooltip-title').html(d.clave);
                    d3.select('#tooltip-' + id + ' .tooltip-info').html(d.valor);
                    d3.select('#tooltip-' + id + ' .tooltip-porcentaje').html(d3.format(".2%")(d.valor / total));

                    var x1 = x(d.valor) + x.bandwidth() / 2 + 10;
                    var y1 = y(d.valor) - 5;
                    d3.select('#tooltip-' + id).attr("transform", "translate(" + x1 + ", " + y1 + ")");
                })
                .on('mouseout', function (d) {
                    $('#tooltip-' + id).attr('class', 'tooltip1');
                })
                ;
    });

}

function barrasDatos(id, data) {
    var div = d3.select("#" + id);
    div.class = 'barras';
    var w = document.getElementById(id).clientWidth;
    div.html('<svg width="' + w + '" height="' + (window.innerWidth * 4 / 12 * 9 / 16 + 20) + '"></svg>');
    var svg = d3.select("#" + id + ">svg");
    var margin = {top: 70, right: 60, bottom: 0, left: 60};
    var width = +w - margin.left - margin.right;
    var height = +(window.innerWidth * 4 / 12 * 9 / 16) - margin.top - margin.bottom;

    var x = d3.scaleBand().rangeRound([0, width]).padding(0.1);
    var y = d3.scaleLinear().rangeRound([height, 0]);



    var g = svg.append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    //tooltip
    var tooltip = svg.append('g')
            .classed('labels-group', true)
            .attr('id', 'tooltip-' + id)
            .attr("class", "tooltip1");

    tooltip.append('rect')
            .attr("width", "150")
            .attr("height", "50")
            .attr("rx", "5")
            .attr("ry", "5")
            .attr("x", -25)
            .attr("y", 10);

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
            .attr("y", 25)
            .text("b");
    tooltip.append('text')
            .attr("class", "tooltip-info")
            .attr("text-anchor", "middle")
            .attr("x", 50)
            .attr("y", 40)
            .text("a");
    tooltip.append('text')
            .attr("class", "tooltip-porcentaje")
            .attr("text-anchor", "middle")
            .attr("x", 50)
            .attr("y", 55)
            .text("c");

    var total=0;
    var maxY=0;
    var domain=[];
    Object.keys(data).forEach(function(v){
        total+=data[v].cantidad;
        if(maxY<data[v].cantidad){
            maxY=data[v].cantidad;
        }
        domain.push(data[v].valor);
    });

    x.domain(domain);
    y.domain([0, maxY]);

    g.append("g")
            .attr("class", "axis axis--x")
            .attr("transform", "translate(0," + height + ")")
            .attr("x", function (d) {
                return x(d);
            })
            .call(d3.axisBottom(x));

    g.append("g")
            .attr("class", "axis axis--y")
            .call(d3.axisLeft(y))
            .append("text")
            .attr("transform", "rotate(-90)")
            .attr("y", 6)
            .attr("dy", "0.71em")
            .attr("text-anchor", "end")
            .text("Frequency");


    g.selectAll(".bar")
            .data(Object.keys(data))
            .enter().append("rect")
            .attr("class", "bar")
            .attr("x", function (d) {
                return x(data[d].valor);
            })
            .attr("y", function (d) {
                return y(data[d].cantidad);
            })
            .attr("width", x.bandwidth())
            .attr("height", function (d) {
                return height - y(data[d].cantidad);
            })
            .on('mouseover', function (d) {
                d3.select('#tooltip-' + id).attr('class', 'tooltip2');
                $('#tooltip-' + id + ' .tooltip-title').empty().append(d);
                $('#tooltip-' + id + ' .tooltip-info').empty().append(data[d].cantidad);
                $('#tooltip-' + id + ' .tooltip-porcentaje').empty().append(d3.format(".2%")(data[d].cantidad / total));

                var x1 = x(data[d].valor) + x.bandwidth() / 2 + 10;
                var y1 = y(data[d].cantidad) - 5;
                d3.select('#tooltip-' + id).attr("transform", "translate(" + x1 + ", " + y1 + ")");
            })
            .on('mouseout', function (d) {
                $('#tooltip-' + id).attr('class', 'tooltip1');
            });
}