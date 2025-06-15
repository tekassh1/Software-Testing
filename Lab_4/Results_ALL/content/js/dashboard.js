/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 37.77777777777778, "KoPercent": 62.22222222222222};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.18888888888888888, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.06666666666666667, 500, 1500, "HTTP Request 2"], "isController": false}, {"data": [0.0, 500, 1500, "HTTP Request 1"], "isController": false}, {"data": [0.5, 500, 1500, "HTTP Request 3"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 720, 448, 62.22222222222222, 980.2083333333328, 509, 1433, 981.5, 1399.0, 1415.0, 1423.0, 6.353912951392566, 1.4333534099332839, 0.9865939055384941], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["HTTP Request 2", 240, 208, 86.66666666666667, 980.2541666666665, 909, 1034, 981.5, 1022.0, 1025.95, 1031.59, 2.1254173345495446, 0.4794642619931101, 0.33002085565759526], "isController": false}, {"data": ["HTTP Request 1", 240, 240, 100.0, 1378.9916666666675, 1306, 1433, 1382.0, 1419.0, 1422.0, 1427.18, 2.117970983797522, 0.4777844699777613, 0.32886463517949804], "isController": false}, {"data": ["HTTP Request 3", 240, 0, 0.0, 581.3791666666667, 509, 636, 585.0, 622.0, 625.0, 633.59, 2.1330489268097583, 0.4811858418877483, 0.33120583922143715], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["The operation lasted too long: It took 1,018 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,328 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 984 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,385 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,001 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, 1.3392857142857142, 0.8333333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 942 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 969 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,343 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 952 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,353 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,415 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 1,405 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, 1.3392857142857142, 0.8333333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 959 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,420 milliseconds, but should not have lasted longer than 940 milliseconds.", 5, 1.1160714285714286, 0.6944444444444444], "isController": false}, {"data": ["The operation lasted too long: It took 1,395 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 1,011 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 994 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,425 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,023 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,390 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, 1.3392857142857142, 0.8333333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 979 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,321 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,306 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 974 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,338 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,028 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,397 milliseconds, but should not have lasted longer than 940 milliseconds.", 5, 1.1160714285714286, 0.6944444444444444], "isController": false}, {"data": ["The operation lasted too long: It took 996 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,355 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 954 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,031 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,403 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 957 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,358 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 989 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 947 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 986 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,387 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,348 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 944 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,345 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,413 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,368 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 967 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,410 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 964 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,326 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,021 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, 1.3392857142857142, 0.8333333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 999 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,323 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,412 milliseconds, but should not have lasted longer than 940 milliseconds.", 5, 1.1160714285714286, 0.6944444444444444], "isController": false}, {"data": ["The operation lasted too long: It took 966 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,382 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,408 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,418 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,378 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,392 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,399 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 970 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,407 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,324 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,393 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 950 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,398 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,422 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 1,372 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 976 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,335 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,314 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 955 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 1,401 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,428 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 960 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,004 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 1,388 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 975 milliseconds, but should not have lasted longer than 940 milliseconds.", 5, 1.1160714285714286, 0.6944444444444444], "isController": false}, {"data": ["The operation lasted too long: It took 1,009 milliseconds, but should not have lasted longer than 940 milliseconds.", 5, 1.1160714285714286, 0.6944444444444444], "isController": false}, {"data": ["The operation lasted too long: It took 1,421 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,379 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,417 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,003 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,334 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,383 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 985 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 965 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,411 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,363 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,032 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,389 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 1,344 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,013 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,019 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,373 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 946 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 948 milliseconds, but should not have lasted longer than 940 milliseconds.", 5, 1.1160714285714286, 0.6944444444444444], "isController": false}, {"data": ["The operation lasted too long: It took 1,322 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,022 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 1,374 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 973 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,354 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,426 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 953 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,017 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,396 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,327 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,007 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,012 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,317 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,332 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 958 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,359 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,364 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,404 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 993 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,394 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, 1.3392857142857142, 0.8333333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 1,320 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,024 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, 1.3392857142857142, 0.8333333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 1,391 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 990 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,034 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,027 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,362 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,406 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,319 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 941 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,342 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 980 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,381 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,416 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,002 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,005 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,329 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,384 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,352 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,419 milliseconds, but should not have lasted longer than 940 milliseconds.", 7, 1.5625, 0.9722222222222222], "isController": false}, {"data": ["The operation lasted too long: It took 951 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,367 milliseconds, but should not have lasted longer than 940 milliseconds.", 5, 1.1160714285714286, 0.6944444444444444], "isController": false}, {"data": ["The operation lasted too long: It took 1,350 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 981 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 1,346 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,015 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,423 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 977 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 998 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,371 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,026 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,357 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 992 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,351 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 997 milliseconds, but should not have lasted longer than 940 milliseconds.", 5, 1.1160714285714286, 0.6944444444444444], "isController": false}, {"data": ["The operation lasted too long: It took 1,020 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,377 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 971 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,030 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,025 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,340 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,356 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,433 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,361 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 987 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,337 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,376 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 978 milliseconds, but should not have lasted longer than 940 milliseconds.", 5, 1.1160714285714286, 0.6944444444444444], "isController": false}, {"data": ["The operation lasted too long: It took 1,010 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 968 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 982 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,414 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, 1.3392857142857142, 0.8333333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 1,380 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,366 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,312 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 962 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 988 milliseconds, but should not have lasted longer than 940 milliseconds.", 4, 0.8928571428571429, 0.5555555555555556], "isController": false}, {"data": ["The operation lasted too long: It took 943 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 1,016 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,000 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,331 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,318 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 1,370 milliseconds, but should not have lasted longer than 940 milliseconds.", 2, 0.44642857142857145, 0.2777777777777778], "isController": false}, {"data": ["The operation lasted too long: It took 972 milliseconds, but should not have lasted longer than 940 milliseconds.", 3, 0.6696428571428571, 0.4166666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 1,347 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}, {"data": ["The operation lasted too long: It took 949 milliseconds, but should not have lasted longer than 940 milliseconds.", 1, 0.22321428571428573, 0.1388888888888889], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 720, 448, "The operation lasted too long: It took 1,419 milliseconds, but should not have lasted longer than 940 milliseconds.", 7, "The operation lasted too long: It took 1,001 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, "The operation lasted too long: It took 1,405 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, "The operation lasted too long: It took 1,390 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, "The operation lasted too long: It took 1,021 milliseconds, but should not have lasted longer than 940 milliseconds.", 6], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["HTTP Request 2", 240, 208, "The operation lasted too long: It took 1,001 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, "The operation lasted too long: It took 1,024 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, "The operation lasted too long: It took 1,021 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, "The operation lasted too long: It took 948 milliseconds, but should not have lasted longer than 940 milliseconds.", 5, "The operation lasted too long: It took 997 milliseconds, but should not have lasted longer than 940 milliseconds.", 5], "isController": false}, {"data": ["HTTP Request 1", 240, 240, "The operation lasted too long: It took 1,419 milliseconds, but should not have lasted longer than 940 milliseconds.", 7, "The operation lasted too long: It took 1,405 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, "The operation lasted too long: It took 1,390 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, "The operation lasted too long: It took 1,394 milliseconds, but should not have lasted longer than 940 milliseconds.", 6, "The operation lasted too long: It took 1,414 milliseconds, but should not have lasted longer than 940 milliseconds.", 6], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
