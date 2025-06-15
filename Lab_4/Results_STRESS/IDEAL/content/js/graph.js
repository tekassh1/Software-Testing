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
$(document).ready(function() {

    $(".click-title").mouseenter( function(    e){
        e.preventDefault();
        this.style.cursor="pointer";
    });
    $(".click-title").mousedown( function(event){
        event.preventDefault();
    });

    // Ugly code while this script is shared among several pages
    try{
        refreshHitsPerSecond(true);
    } catch(e){}
    try{
        refreshResponseTimeOverTime(true);
    } catch(e){}
    try{
        refreshResponseTimePercentiles();
    } catch(e){}
});


var responseTimePercentilesInfos = {
        data: {"result": {"minY": 512.0, "minX": 0.0, "maxY": 889.0, "series": [{"data": [[0.0, 512.0], [0.1, 515.0], [0.2, 516.0], [0.3, 518.0], [0.4, 522.0], [0.5, 523.0], [0.6, 526.0], [0.7, 528.0], [0.8, 530.0], [0.9, 531.0], [1.0, 533.0], [1.1, 535.0], [1.2, 535.0], [1.3, 536.0], [1.4, 537.0], [1.5, 538.0], [1.6, 538.0], [1.7, 541.0], [1.8, 542.0], [1.9, 542.0], [2.0, 542.0], [2.1, 542.0], [2.2, 543.0], [2.3, 544.0], [2.4, 547.0], [2.5, 547.0], [2.6, 547.0], [2.7, 548.0], [2.8, 550.0], [2.9, 550.0], [3.0, 550.0], [3.1, 550.0], [3.2, 551.0], [3.3, 552.0], [3.4, 552.0], [3.5, 553.0], [3.6, 553.0], [3.7, 554.0], [3.8, 556.0], [3.9, 556.0], [4.0, 556.0], [4.1, 559.0], [4.2, 559.0], [4.3, 560.0], [4.4, 560.0], [4.5, 562.0], [4.6, 563.0], [4.7, 565.0], [4.8, 567.0], [4.9, 567.0], [5.0, 567.0], [5.1, 568.0], [5.2, 569.0], [5.3, 570.0], [5.4, 570.0], [5.5, 571.0], [5.6, 571.0], [5.7, 573.0], [5.8, 573.0], [5.9, 574.0], [6.0, 574.0], [6.1, 575.0], [6.2, 575.0], [6.3, 577.0], [6.4, 577.0], [6.5, 578.0], [6.6, 579.0], [6.7, 580.0], [6.8, 580.0], [6.9, 580.0], [7.0, 582.0], [7.1, 583.0], [7.2, 583.0], [7.3, 583.0], [7.4, 585.0], [7.5, 586.0], [7.6, 586.0], [7.7, 588.0], [7.8, 588.0], [7.9, 588.0], [8.0, 590.0], [8.1, 590.0], [8.2, 590.0], [8.3, 590.0], [8.4, 591.0], [8.5, 592.0], [8.6, 592.0], [8.7, 592.0], [8.8, 592.0], [8.9, 593.0], [9.0, 593.0], [9.1, 594.0], [9.2, 595.0], [9.3, 596.0], [9.4, 596.0], [9.5, 596.0], [9.6, 597.0], [9.7, 598.0], [9.8, 598.0], [9.9, 598.0], [10.0, 599.0], [10.1, 599.0], [10.2, 599.0], [10.3, 599.0], [10.4, 600.0], [10.5, 600.0], [10.6, 600.0], [10.7, 600.0], [10.8, 602.0], [10.9, 602.0], [11.0, 603.0], [11.1, 603.0], [11.2, 604.0], [11.3, 604.0], [11.4, 605.0], [11.5, 605.0], [11.6, 605.0], [11.7, 608.0], [11.8, 608.0], [11.9, 608.0], [12.0, 608.0], [12.1, 609.0], [12.2, 609.0], [12.3, 609.0], [12.4, 610.0], [12.5, 610.0], [12.6, 610.0], [12.7, 612.0], [12.8, 612.0], [12.9, 612.0], [13.0, 613.0], [13.1, 613.0], [13.2, 614.0], [13.3, 614.0], [13.4, 615.0], [13.5, 616.0], [13.6, 616.0], [13.7, 617.0], [13.8, 618.0], [13.9, 618.0], [14.0, 619.0], [14.1, 619.0], [14.2, 620.0], [14.3, 621.0], [14.4, 622.0], [14.5, 622.0], [14.6, 623.0], [14.7, 624.0], [14.8, 624.0], [14.9, 624.0], [15.0, 624.0], [15.1, 625.0], [15.2, 627.0], [15.3, 628.0], [15.4, 628.0], [15.5, 629.0], [15.6, 630.0], [15.7, 630.0], [15.8, 630.0], [15.9, 630.0], [16.0, 632.0], [16.1, 632.0], [16.2, 633.0], [16.3, 633.0], [16.4, 633.0], [16.5, 634.0], [16.6, 634.0], [16.7, 635.0], [16.8, 636.0], [16.9, 636.0], [17.0, 638.0], [17.1, 638.0], [17.2, 639.0], [17.3, 639.0], [17.4, 641.0], [17.5, 641.0], [17.6, 641.0], [17.7, 641.0], [17.8, 642.0], [17.9, 642.0], [18.0, 644.0], [18.1, 644.0], [18.2, 644.0], [18.3, 644.0], [18.4, 647.0], [18.5, 647.0], [18.6, 647.0], [18.7, 649.0], [18.8, 650.0], [18.9, 651.0], [19.0, 652.0], [19.1, 652.0], [19.2, 652.0], [19.3, 654.0], [19.4, 654.0], [19.5, 654.0], [19.6, 654.0], [19.7, 656.0], [19.8, 657.0], [19.9, 657.0], [20.0, 658.0], [20.1, 658.0], [20.2, 659.0], [20.3, 659.0], [20.4, 659.0], [20.5, 659.0], [20.6, 660.0], [20.7, 661.0], [20.8, 662.0], [20.9, 663.0], [21.0, 664.0], [21.1, 664.0], [21.2, 664.0], [21.3, 664.0], [21.4, 664.0], [21.5, 666.0], [21.6, 667.0], [21.7, 667.0], [21.8, 668.0], [21.9, 668.0], [22.0, 670.0], [22.1, 670.0], [22.2, 671.0], [22.3, 672.0], [22.4, 672.0], [22.5, 672.0], [22.6, 673.0], [22.7, 673.0], [22.8, 674.0], [22.9, 675.0], [23.0, 676.0], [23.1, 676.0], [23.2, 676.0], [23.3, 677.0], [23.4, 677.0], [23.5, 678.0], [23.6, 678.0], [23.7, 678.0], [23.8, 680.0], [23.9, 681.0], [24.0, 682.0], [24.1, 682.0], [24.2, 683.0], [24.3, 683.0], [24.4, 683.0], [24.5, 685.0], [24.6, 685.0], [24.7, 687.0], [24.8, 687.0], [24.9, 688.0], [25.0, 689.0], [25.1, 689.0], [25.2, 690.0], [25.3, 691.0], [25.4, 691.0], [25.5, 692.0], [25.6, 692.0], [25.7, 694.0], [25.8, 694.0], [25.9, 694.0], [26.0, 695.0], [26.1, 695.0], [26.2, 696.0], [26.3, 696.0], [26.4, 697.0], [26.5, 698.0], [26.6, 698.0], [26.7, 699.0], [26.8, 700.0], [26.9, 700.0], [27.0, 701.0], [27.1, 702.0], [27.2, 702.0], [27.3, 702.0], [27.4, 704.0], [27.5, 705.0], [27.6, 705.0], [27.7, 705.0], [27.8, 706.0], [27.9, 706.0], [28.0, 707.0], [28.1, 708.0], [28.2, 709.0], [28.3, 709.0], [28.4, 709.0], [28.5, 710.0], [28.6, 710.0], [28.7, 710.0], [28.8, 711.0], [28.9, 711.0], [29.0, 712.0], [29.1, 712.0], [29.2, 713.0], [29.3, 713.0], [29.4, 713.0], [29.5, 714.0], [29.6, 714.0], [29.7, 715.0], [29.8, 715.0], [29.9, 715.0], [30.0, 715.0], [30.1, 715.0], [30.2, 717.0], [30.3, 717.0], [30.4, 719.0], [30.5, 719.0], [30.6, 720.0], [30.7, 720.0], [30.8, 720.0], [30.9, 720.0], [31.0, 722.0], [31.1, 722.0], [31.2, 722.0], [31.3, 723.0], [31.4, 724.0], [31.5, 724.0], [31.6, 725.0], [31.7, 727.0], [31.8, 727.0], [31.9, 727.0], [32.0, 728.0], [32.1, 728.0], [32.2, 728.0], [32.3, 730.0], [32.4, 730.0], [32.5, 731.0], [32.6, 731.0], [32.7, 732.0], [32.8, 732.0], [32.9, 734.0], [33.0, 735.0], [33.1, 735.0], [33.2, 735.0], [33.3, 736.0], [33.4, 737.0], [33.5, 738.0], [33.6, 739.0], [33.7, 739.0], [33.8, 740.0], [33.9, 741.0], [34.0, 742.0], [34.1, 742.0], [34.2, 742.0], [34.3, 744.0], [34.4, 746.0], [34.5, 746.0], [34.6, 746.0], [34.7, 747.0], [34.8, 747.0], [34.9, 747.0], [35.0, 748.0], [35.1, 748.0], [35.2, 748.0], [35.3, 749.0], [35.4, 749.0], [35.5, 749.0], [35.6, 750.0], [35.7, 751.0], [35.8, 752.0], [35.9, 752.0], [36.0, 753.0], [36.1, 753.0], [36.2, 753.0], [36.3, 753.0], [36.4, 755.0], [36.5, 755.0], [36.6, 756.0], [36.7, 758.0], [36.8, 759.0], [36.9, 759.0], [37.0, 760.0], [37.1, 760.0], [37.2, 761.0], [37.3, 761.0], [37.4, 761.0], [37.5, 762.0], [37.6, 762.0], [37.7, 763.0], [37.8, 763.0], [37.9, 763.0], [38.0, 765.0], [38.1, 765.0], [38.2, 767.0], [38.3, 767.0], [38.4, 768.0], [38.5, 768.0], [38.6, 768.0], [38.7, 770.0], [38.8, 770.0], [38.9, 771.0], [39.0, 771.0], [39.1, 772.0], [39.2, 772.0], [39.3, 773.0], [39.4, 773.0], [39.5, 773.0], [39.6, 773.0], [39.7, 775.0], [39.8, 776.0], [39.9, 776.0], [40.0, 777.0], [40.1, 777.0], [40.2, 777.0], [40.3, 778.0], [40.4, 778.0], [40.5, 778.0], [40.6, 779.0], [40.7, 780.0], [40.8, 780.0], [40.9, 781.0], [41.0, 781.0], [41.1, 781.0], [41.2, 782.0], [41.3, 782.0], [41.4, 783.0], [41.5, 783.0], [41.6, 784.0], [41.7, 784.0], [41.8, 785.0], [41.9, 785.0], [42.0, 785.0], [42.1, 788.0], [42.2, 788.0], [42.3, 788.0], [42.4, 788.0], [42.5, 788.0], [42.6, 790.0], [42.7, 791.0], [42.8, 791.0], [42.9, 792.0], [43.0, 792.0], [43.1, 793.0], [43.2, 793.0], [43.3, 793.0], [43.4, 793.0], [43.5, 794.0], [43.6, 794.0], [43.7, 796.0], [43.8, 796.0], [43.9, 796.0], [44.0, 796.0], [44.1, 797.0], [44.2, 797.0], [44.3, 798.0], [44.4, 799.0], [44.5, 799.0], [44.6, 799.0], [44.7, 799.0], [44.8, 799.0], [44.9, 799.0], [45.0, 800.0], [45.1, 800.0], [45.2, 800.0], [45.3, 801.0], [45.4, 801.0], [45.5, 801.0], [45.6, 801.0], [45.7, 801.0], [45.8, 802.0], [45.9, 802.0], [46.0, 802.0], [46.1, 803.0], [46.2, 804.0], [46.3, 804.0], [46.4, 806.0], [46.5, 807.0], [46.6, 807.0], [46.7, 807.0], [46.8, 807.0], [46.9, 808.0], [47.0, 808.0], [47.1, 809.0], [47.2, 810.0], [47.3, 810.0], [47.4, 810.0], [47.5, 810.0], [47.6, 810.0], [47.7, 811.0], [47.8, 812.0], [47.9, 812.0], [48.0, 812.0], [48.1, 813.0], [48.2, 813.0], [48.3, 813.0], [48.4, 815.0], [48.5, 815.0], [48.6, 815.0], [48.7, 815.0], [48.8, 815.0], [48.9, 815.0], [49.0, 816.0], [49.1, 816.0], [49.2, 817.0], [49.3, 817.0], [49.4, 818.0], [49.5, 818.0], [49.6, 818.0], [49.7, 819.0], [49.8, 819.0], [49.9, 820.0], [50.0, 820.0], [50.1, 821.0], [50.2, 822.0], [50.3, 822.0], [50.4, 823.0], [50.5, 824.0], [50.6, 824.0], [50.7, 824.0], [50.8, 824.0], [50.9, 824.0], [51.0, 824.0], [51.1, 824.0], [51.2, 825.0], [51.3, 825.0], [51.4, 825.0], [51.5, 826.0], [51.6, 826.0], [51.7, 826.0], [51.8, 826.0], [51.9, 826.0], [52.0, 826.0], [52.1, 826.0], [52.2, 826.0], [52.3, 826.0], [52.4, 827.0], [52.5, 827.0], [52.6, 829.0], [52.7, 829.0], [52.8, 829.0], [52.9, 829.0], [53.0, 829.0], [53.1, 829.0], [53.2, 829.0], [53.3, 830.0], [53.4, 830.0], [53.5, 830.0], [53.6, 830.0], [53.7, 831.0], [53.8, 831.0], [53.9, 831.0], [54.0, 832.0], [54.1, 832.0], [54.2, 832.0], [54.3, 832.0], [54.4, 832.0], [54.5, 832.0], [54.6, 832.0], [54.7, 833.0], [54.8, 833.0], [54.9, 833.0], [55.0, 834.0], [55.1, 834.0], [55.2, 834.0], [55.3, 834.0], [55.4, 835.0], [55.5, 835.0], [55.6, 835.0], [55.7, 835.0], [55.8, 835.0], [55.9, 835.0], [56.0, 835.0], [56.1, 835.0], [56.2, 835.0], [56.3, 836.0], [56.4, 837.0], [56.5, 837.0], [56.6, 837.0], [56.7, 837.0], [56.8, 837.0], [56.9, 837.0], [57.0, 837.0], [57.1, 837.0], [57.2, 837.0], [57.3, 837.0], [57.4, 837.0], [57.5, 837.0], [57.6, 838.0], [57.7, 838.0], [57.8, 838.0], [57.9, 838.0], [58.0, 838.0], [58.1, 838.0], [58.2, 838.0], [58.3, 838.0], [58.4, 838.0], [58.5, 838.0], [58.6, 838.0], [58.7, 838.0], [58.8, 838.0], [58.9, 838.0], [59.0, 838.0], [59.1, 838.0], [59.2, 838.0], [59.3, 838.0], [59.4, 838.0], [59.5, 838.0], [59.6, 838.0], [59.7, 838.0], [59.8, 838.0], [59.9, 838.0], [60.0, 838.0], [60.1, 838.0], [60.2, 838.0], [60.3, 838.0], [60.4, 838.0], [60.5, 838.0], [60.6, 838.0], [60.7, 838.0], [60.8, 838.0], [60.9, 838.0], [61.0, 838.0], [61.1, 838.0], [61.2, 838.0], [61.3, 838.0], [61.4, 838.0], [61.5, 838.0], [61.6, 838.0], [61.7, 838.0], [61.8, 838.0], [61.9, 838.0], [62.0, 838.0], [62.1, 838.0], [62.2, 838.0], [62.3, 839.0], [62.4, 839.0], [62.5, 839.0], [62.6, 839.0], [62.7, 839.0], [62.8, 839.0], [62.9, 839.0], [63.0, 839.0], [63.1, 839.0], [63.2, 839.0], [63.3, 839.0], [63.4, 839.0], [63.5, 839.0], [63.6, 839.0], [63.7, 839.0], [63.8, 839.0], [63.9, 839.0], [64.0, 839.0], [64.1, 839.0], [64.2, 839.0], [64.3, 840.0], [64.4, 840.0], [64.5, 840.0], [64.6, 840.0], [64.7, 840.0], [64.8, 840.0], [64.9, 841.0], [65.0, 841.0], [65.1, 841.0], [65.2, 841.0], [65.3, 841.0], [65.4, 841.0], [65.5, 841.0], [65.6, 841.0], [65.7, 841.0], [65.8, 841.0], [65.9, 841.0], [66.0, 841.0], [66.1, 841.0], [66.2, 841.0], [66.3, 841.0], [66.4, 841.0], [66.5, 841.0], [66.6, 841.0], [66.7, 841.0], [66.8, 841.0], [66.9, 841.0], [67.0, 841.0], [67.1, 841.0], [67.2, 841.0], [67.3, 841.0], [67.4, 841.0], [67.5, 841.0], [67.6, 841.0], [67.7, 841.0], [67.8, 841.0], [67.9, 841.0], [68.0, 841.0], [68.1, 841.0], [68.2, 841.0], [68.3, 841.0], [68.4, 841.0], [68.5, 841.0], [68.6, 841.0], [68.7, 842.0], [68.8, 842.0], [68.9, 842.0], [69.0, 842.0], [69.1, 842.0], [69.2, 842.0], [69.3, 842.0], [69.4, 842.0], [69.5, 842.0], [69.6, 842.0], [69.7, 842.0], [69.8, 842.0], [69.9, 842.0], [70.0, 842.0], [70.1, 842.0], [70.2, 843.0], [70.3, 843.0], [70.4, 843.0], [70.5, 843.0], [70.6, 843.0], [70.7, 843.0], [70.8, 843.0], [70.9, 843.0], [71.0, 843.0], [71.1, 843.0], [71.2, 843.0], [71.3, 843.0], [71.4, 843.0], [71.5, 843.0], [71.6, 843.0], [71.7, 843.0], [71.8, 843.0], [71.9, 843.0], [72.0, 843.0], [72.1, 843.0], [72.2, 843.0], [72.3, 843.0], [72.4, 844.0], [72.5, 844.0], [72.6, 844.0], [72.7, 844.0], [72.8, 844.0], [72.9, 844.0], [73.0, 844.0], [73.1, 844.0], [73.2, 844.0], [73.3, 844.0], [73.4, 844.0], [73.5, 844.0], [73.6, 844.0], [73.7, 844.0], [73.8, 844.0], [73.9, 844.0], [74.0, 844.0], [74.1, 844.0], [74.2, 844.0], [74.3, 844.0], [74.4, 844.0], [74.5, 844.0], [74.6, 844.0], [74.7, 844.0], [74.8, 844.0], [74.9, 844.0], [75.0, 844.0], [75.1, 844.0], [75.2, 844.0], [75.3, 844.0], [75.4, 844.0], [75.5, 844.0], [75.6, 844.0], [75.7, 844.0], [75.8, 844.0], [75.9, 844.0], [76.0, 844.0], [76.1, 844.0], [76.2, 844.0], [76.3, 844.0], [76.4, 844.0], [76.5, 844.0], [76.6, 845.0], [76.7, 845.0], [76.8, 845.0], [76.9, 845.0], [77.0, 845.0], [77.1, 845.0], [77.2, 845.0], [77.3, 845.0], [77.4, 846.0], [77.5, 846.0], [77.6, 846.0], [77.7, 846.0], [77.8, 846.0], [77.9, 846.0], [78.0, 846.0], [78.1, 846.0], [78.2, 846.0], [78.3, 846.0], [78.4, 846.0], [78.5, 846.0], [78.6, 846.0], [78.7, 846.0], [78.8, 846.0], [78.9, 846.0], [79.0, 846.0], [79.1, 846.0], [79.2, 846.0], [79.3, 846.0], [79.4, 846.0], [79.5, 847.0], [79.6, 847.0], [79.7, 847.0], [79.8, 847.0], [79.9, 847.0], [80.0, 847.0], [80.1, 847.0], [80.2, 847.0], [80.3, 847.0], [80.4, 847.0], [80.5, 847.0], [80.6, 847.0], [80.7, 847.0], [80.8, 847.0], [80.9, 847.0], [81.0, 847.0], [81.1, 847.0], [81.2, 847.0], [81.3, 847.0], [81.4, 847.0], [81.5, 847.0], [81.6, 847.0], [81.7, 847.0], [81.8, 847.0], [81.9, 847.0], [82.0, 847.0], [82.1, 847.0], [82.2, 847.0], [82.3, 847.0], [82.4, 847.0], [82.5, 847.0], [82.6, 847.0], [82.7, 847.0], [82.8, 847.0], [82.9, 847.0], [83.0, 847.0], [83.1, 847.0], [83.2, 847.0], [83.3, 847.0], [83.4, 847.0], [83.5, 847.0], [83.6, 847.0], [83.7, 847.0], [83.8, 847.0], [83.9, 847.0], [84.0, 847.0], [84.1, 847.0], [84.2, 847.0], [84.3, 848.0], [84.4, 848.0], [84.5, 848.0], [84.6, 848.0], [84.7, 848.0], [84.8, 848.0], [84.9, 848.0], [85.0, 848.0], [85.1, 848.0], [85.2, 848.0], [85.3, 848.0], [85.4, 848.0], [85.5, 848.0], [85.6, 848.0], [85.7, 848.0], [85.8, 848.0], [85.9, 848.0], [86.0, 848.0], [86.1, 849.0], [86.2, 849.0], [86.3, 849.0], [86.4, 849.0], [86.5, 849.0], [86.6, 849.0], [86.7, 849.0], [86.8, 849.0], [86.9, 849.0], [87.0, 849.0], [87.1, 849.0], [87.2, 849.0], [87.3, 849.0], [87.4, 849.0], [87.5, 850.0], [87.6, 850.0], [87.7, 850.0], [87.8, 850.0], [87.9, 850.0], [88.0, 850.0], [88.1, 850.0], [88.2, 850.0], [88.3, 850.0], [88.4, 850.0], [88.5, 850.0], [88.6, 850.0], [88.7, 850.0], [88.8, 850.0], [88.9, 850.0], [89.0, 850.0], [89.1, 850.0], [89.2, 850.0], [89.3, 850.0], [89.4, 851.0], [89.5, 851.0], [89.6, 851.0], [89.7, 851.0], [89.8, 851.0], [89.9, 851.0], [90.0, 851.0], [90.1, 851.0], [90.2, 851.0], [90.3, 851.0], [90.4, 851.0], [90.5, 851.0], [90.6, 852.0], [90.7, 852.0], [90.8, 852.0], [90.9, 852.0], [91.0, 852.0], [91.1, 852.0], [91.2, 852.0], [91.3, 852.0], [91.4, 852.0], [91.5, 852.0], [91.6, 852.0], [91.7, 852.0], [91.8, 852.0], [91.9, 853.0], [92.0, 853.0], [92.1, 853.0], [92.2, 853.0], [92.3, 853.0], [92.4, 853.0], [92.5, 853.0], [92.6, 853.0], [92.7, 853.0], [92.8, 854.0], [92.9, 854.0], [93.0, 854.0], [93.1, 854.0], [93.2, 854.0], [93.3, 854.0], [93.4, 854.0], [93.5, 855.0], [93.6, 855.0], [93.7, 855.0], [93.8, 855.0], [93.9, 855.0], [94.0, 856.0], [94.1, 857.0], [94.2, 857.0], [94.3, 857.0], [94.4, 858.0], [94.5, 858.0], [94.6, 858.0], [94.7, 860.0], [94.8, 860.0], [94.9, 861.0], [95.0, 862.0], [95.1, 863.0], [95.2, 863.0], [95.3, 863.0], [95.4, 865.0], [95.5, 865.0], [95.6, 865.0], [95.7, 866.0], [95.8, 866.0], [95.9, 867.0], [96.0, 867.0], [96.1, 867.0], [96.2, 867.0], [96.3, 867.0], [96.4, 868.0], [96.5, 868.0], [96.6, 868.0], [96.7, 869.0], [96.8, 869.0], [96.9, 869.0], [97.0, 869.0], [97.1, 870.0], [97.2, 870.0], [97.3, 871.0], [97.4, 871.0], [97.5, 871.0], [97.6, 871.0], [97.7, 872.0], [97.8, 872.0], [97.9, 872.0], [98.0, 873.0], [98.1, 873.0], [98.2, 873.0], [98.3, 874.0], [98.4, 875.0], [98.5, 875.0], [98.6, 875.0], [98.7, 876.0], [98.8, 876.0], [98.9, 876.0], [99.0, 877.0], [99.1, 878.0], [99.2, 878.0], [99.3, 879.0], [99.4, 880.0], [99.5, 880.0], [99.6, 880.0], [99.7, 880.0], [99.8, 884.0], [99.9, 885.0], [100.0, 889.0]], "isOverall": false, "label": "HTTP Request 3", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
        getOptions: function() {
            return {
                series: {
                    points: { show: false }
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentiles'
                },
                xaxis: {
                    tickDecimals: 1,
                    axisLabel: "Percentiles",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Percentile value in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : %x.2 percentile was %y ms"
                },
                selection: { mode: "xy" },
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentiles"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesPercentiles"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesPercentiles"), dataset, prepareOverviewOptions(options));
        }
};

/**
 * @param elementId Id of element where we display message
 */
function setEmptyGraph(elementId) {
    $(function() {
        $(elementId).text("No graph series with filter="+seriesFilter);
    });
}

// Response times percentiles
function refreshResponseTimePercentiles() {
    var infos = responseTimePercentilesInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimePercentiles");
        return;
    }
    if (isGraph($("#flotResponseTimesPercentiles"))){
        infos.createGraph();
    } else {
        var choiceContainer = $("#choicesResponseTimePercentiles");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesPercentiles", "#overviewResponseTimesPercentiles");
        $('#bodyResponseTimePercentiles .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimeDistributionInfos = {
        data: {"result": {"minY": 135.0, "minX": 500.0, "maxY": 716.0, "series": [{"data": [[600.0, 213.0], [700.0, 236.0], [800.0, 716.0], [500.0, 135.0]], "isOverall": false, "label": "HTTP Request 3", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 800.0, "title": "Response Time Distribution"}},
        getOptions: function() {
            var granularity = this.data.result.granularity;
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    barWidth: this.data.result.granularity
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " responses for " + label + " were between " + xval + " and " + (xval + granularity) + " ms";
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimeDistribution"), prepareData(data.result.series, $("#choicesResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshResponseTimeDistribution() {
    var infos = responseTimeDistributionInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeDistribution");
        return;
    }
    if (isGraph($("#flotResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var syntheticResponseTimeDistributionInfos = {
        data: {"result": {"minY": 1300.0, "minX": 1.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1 500ms"], [2, "Requests having \nresponse time > 1 500ms"], [3, "Requests in error"]], "maxY": 1300.0, "series": [{"data": [], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 1300.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1 500ms", "isController": false}, {"data": [], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1 500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 1.0, "title": "Synthetic Response Times Distribution"}},
        getOptions: function() {
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendSyntheticResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times ranges",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                    tickLength:0,
                    min:-0.5,
                    max:3.5
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    align: "center",
                    barWidth: 0.25,
                    fill:.75
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " " + label;
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            options.xaxis.ticks = data.result.ticks;
            $.plot($("#flotSyntheticResponseTimeDistribution"), prepareData(data.result.series, $("#choicesSyntheticResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshSyntheticResponseTimeDistribution() {
    var infos = syntheticResponseTimeDistributionInfos;
    prepareSeries(infos.data, true);
    if (isGraph($("#flotSyntheticResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerSyntheticResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var activeThreadsOverTimeInfos = {
        data: {"result": {"minY": 46.801339285714356, "minX": 1.7500248E12, "maxY": 56.02816901408446, "series": [{"data": [[1.7500248E12, 56.02816901408446], [1.75002486E12, 46.801339285714356]], "isOverall": false, "label": "High cost config", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75002486E12, "title": "Active Threads Over Time"}},
        getOptions: function() {
            return {
                series: {
                    stack: true,
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 6,
                    show: true,
                    container: '#legendActiveThreadsOverTime'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                selection: {
                    mode: 'xy'
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : At %x there were %y active threads"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesActiveThreadsOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotActiveThreadsOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewActiveThreadsOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Active Threads Over Time
function refreshActiveThreadsOverTime(fixTimestamps) {
    var infos = activeThreadsOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotActiveThreadsOverTime"))) {
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesActiveThreadsOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotActiveThreadsOverTime", "#overviewActiveThreadsOverTime");
        $('#footerActiveThreadsOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var timeVsThreadsInfos = {
        data: {"result": {"minY": 531.5, "minX": 1.0, "maxY": 838.1325695581014, "series": [{"data": [[2.0, 531.5], [3.0, 550.5], [4.0, 576.0], [5.0, 559.5], [6.0, 586.5], [7.0, 572.3333333333334], [8.0, 561.75], [9.0, 566.0], [10.0, 559.0], [11.0, 572.25], [12.0, 573.75], [13.0, 572.25], [14.0, 580.5], [15.0, 573.1666666666666], [16.0, 558.6], [17.0, 571.8333333333334], [18.0, 578.0], [19.0, 590.0], [20.0, 599.5714285714286], [21.0, 588.5], [22.0, 589.7142857142858], [23.0, 603.8888888888889], [24.0, 602.5714285714286], [25.0, 609.625], [26.0, 621.8999999999999], [27.0, 625.5555555555555], [28.0, 616.8000000000002], [29.0, 623.8000000000001], [30.0, 624.3000000000001], [31.0, 633.4], [32.0, 637.4], [33.0, 648.3636363636364], [34.0, 645.9166666666666], [35.0, 652.0833333333334], [36.0, 656.5833333333334], [37.0, 663.25], [38.0, 670.4166666666665], [39.0, 676.75], [40.0, 690.5714285714284], [41.0, 687.4285714285713], [42.0, 693.0714285714287], [43.0, 702.9285714285714], [44.0, 706.8571428571429], [45.0, 714.8571428571429], [46.0, 723.0666666666667], [47.0, 726.625], [48.0, 724.0666666666667], [49.0, 743.0000000000001], [50.0, 744.0], [51.0, 749.7499999999999], [52.0, 762.5000000000001], [53.0, 762.5882352941177], [54.0, 761.9444444444446], [55.0, 771.1666666666666], [56.0, 773.7058823529413], [57.0, 776.5624999999999], [58.0, 785.7368421052631], [59.0, 787.4117647058822], [60.0, 794.2999999999998], [61.0, 788.9473684210527], [62.0, 798.0], [63.0, 799.1], [64.0, 803.45], [65.0, 838.1325695581014], [1.0, 559.0]], "isOverall": false, "label": "HTTP Request 3", "isController": false}, {"data": [[52.84846153846155, 765.2076923076928]], "isOverall": false, "label": "HTTP Request 3-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 65.0, "title": "Time VS Threads"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: { noColumns: 2,show: true, container: '#legendTimeVsThreads' },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s: At %x.2 active threads, Average response time was %y.2 ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesTimeVsThreads"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotTimesVsThreads"), dataset, options);
            // setup overview
            $.plot($("#overviewTimesVsThreads"), dataset, prepareOverviewOptions(options));
        }
};

// Time vs threads
function refreshTimeVsThreads(){
    var infos = timeVsThreadsInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTimeVsThreads");
        return;
    }
    if(isGraph($("#flotTimesVsThreads"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTimeVsThreads");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTimesVsThreads", "#overviewTimesVsThreads");
        $('#footerTimeVsThreads .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var bytesThroughputOverTimeInfos = {
        data : {"result": {"minY": 1187.2, "minX": 1.7500248E12, "maxY": 3280.2, "series": [{"data": [[1.7500248E12, 3280.2], [1.75002486E12, 1724.8]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.7500248E12, 2257.8], [1.75002486E12, 1187.2]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75002486E12, "title": "Bytes Throughput Over Time"}},
        getOptions : function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity) ,
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Bytes / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendBytesThroughputOverTime'
                },
                selection: {
                    mode: "xy"
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y"
                }
            };
        },
        createGraph : function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesBytesThroughputOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotBytesThroughputOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewBytesThroughputOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Bytes throughput Over Time
function refreshBytesThroughputOverTime(fixTimestamps) {
    var infos = bytesThroughputOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotBytesThroughputOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesBytesThroughputOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotBytesThroughputOverTime", "#overviewBytesThroughputOverTime");
        $('#footerBytesThroughputOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimesOverTimeInfos = {
        data: {"result": {"minY": 759.1666666666669, "minX": 1.7500248E12, "maxY": 776.6964285714284, "series": [{"data": [[1.7500248E12, 759.1666666666669], [1.75002486E12, 776.6964285714284]], "isOverall": false, "label": "HTTP Request 3", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75002486E12, "title": "Response Time Over Time"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average response time was %y ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Times Over Time
function refreshResponseTimeOverTime(fixTimestamps) {
    var infos = responseTimesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotResponseTimesOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesOverTime", "#overviewResponseTimesOverTime");
        $('#footerResponseTimesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var latenciesOverTimeInfos = {
        data: {"result": {"minY": 759.149061032865, "minX": 1.7500248E12, "maxY": 776.6897321428572, "series": [{"data": [[1.7500248E12, 759.149061032865], [1.75002486E12, 776.6897321428572]], "isOverall": false, "label": "HTTP Request 3", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75002486E12, "title": "Latencies Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response latencies in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendLatenciesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average latency was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesLatenciesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotLatenciesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewLatenciesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Latencies Over Time
function refreshLatenciesOverTime(fixTimestamps) {
    var infos = latenciesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyLatenciesOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotLatenciesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesLatenciesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotLatenciesOverTime", "#overviewLatenciesOverTime");
        $('#footerLatenciesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var connectTimeOverTimeInfos = {
        data: {"result": {"minY": 0.0, "minX": 1.7500248E12, "maxY": 0.08685446009389675, "series": [{"data": [[1.7500248E12, 0.08685446009389675], [1.75002486E12, 0.0]], "isOverall": false, "label": "HTTP Request 3", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75002486E12, "title": "Connect Time Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getConnectTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average Connect Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendConnectTimeOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average connect time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesConnectTimeOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotConnectTimeOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewConnectTimeOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Connect Time Over Time
function refreshConnectTimeOverTime(fixTimestamps) {
    var infos = connectTimeOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyConnectTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotConnectTimeOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesConnectTimeOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotConnectTimeOverTime", "#overviewConnectTimeOverTime");
        $('#footerConnectTimeOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var responseTimePercentilesOverTimeInfos = {
        data: {"result": {"minY": 512.0, "minX": 1.7500248E12, "maxY": 889.0, "series": [{"data": [[1.7500248E12, 889.0], [1.75002486E12, 885.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.7500248E12, 512.0], [1.75002486E12, 547.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.7500248E12, 852.0], [1.75002486E12, 850.0]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.7500248E12, 878.47], [1.75002486E12, 877.02]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.7500248E12, 818.0], [1.75002486E12, 822.5]], "isOverall": false, "label": "Median", "isController": false}, {"data": [[1.7500248E12, 863.0], [1.75002486E12, 861.6499999999999]], "isOverall": false, "label": "95th percentile", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75002486E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Response Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentilesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Response time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentilesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimePercentilesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimePercentilesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Time Percentiles Over Time
function refreshResponseTimePercentilesOverTime(fixTimestamps) {
    var infos = responseTimePercentilesOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotResponseTimePercentilesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimePercentilesOverTime", "#overviewResponseTimePercentilesOverTime");
        $('#footerResponseTimePercentilesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var responseTimeVsRequestInfos = {
    data: {"result": {"minY": 555.0, "minX": 2.0, "maxY": 844.0, "series": [{"data": [[2.0, 555.5], [8.0, 555.0], [9.0, 652.0], [10.0, 591.5], [11.0, 661.0], [3.0, 600.0], [12.0, 656.0], [13.0, 633.0], [14.0, 706.5], [15.0, 665.5], [4.0, 568.5], [16.0, 798.0], [17.0, 644.0], [18.0, 760.0], [19.0, 685.0], [5.0, 573.0], [20.0, 838.5], [21.0, 841.0], [22.0, 844.0], [23.0, 844.0], [6.0, 590.5], [24.0, 844.0], [25.0, 797.0], [7.0, 597.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 25.0, "title": "Response Time Vs Request"}},
    getOptions: function() {
        return {
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Response Time in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: {
                noColumns: 2,
                show: true,
                container: '#legendResponseTimeVsRequest'
            },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median response time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesResponseTimeVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotResponseTimeVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewResponseTimeVsRequest"), dataset, prepareOverviewOptions(options));

    }
};

// Response Time vs Request
function refreshResponseTimeVsRequest() {
    var infos = responseTimeVsRequestInfos;
    prepareSeries(infos.data);
    if (isGraph($("#flotResponseTimeVsRequest"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeVsRequest");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimeVsRequest", "#overviewResponseTimeVsRequest");
        $('#footerResponseRimeVsRequest .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var latenciesVsRequestInfos = {
    data: {"result": {"minY": 555.0, "minX": 2.0, "maxY": 844.0, "series": [{"data": [[2.0, 555.5], [8.0, 555.0], [9.0, 652.0], [10.0, 591.5], [11.0, 661.0], [3.0, 600.0], [12.0, 656.0], [13.0, 633.0], [14.0, 706.5], [15.0, 665.5], [4.0, 568.5], [16.0, 798.0], [17.0, 644.0], [18.0, 760.0], [19.0, 685.0], [5.0, 572.5], [20.0, 838.5], [21.0, 841.0], [22.0, 844.0], [23.0, 844.0], [6.0, 590.5], [24.0, 844.0], [25.0, 797.0], [7.0, 597.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 25.0, "title": "Latencies Vs Request"}},
    getOptions: function() {
        return{
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Latency in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: { noColumns: 2,show: true, container: '#legendLatencyVsRequest' },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median Latency time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesLatencyVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotLatenciesVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewLatenciesVsRequest"), dataset, prepareOverviewOptions(options));
    }
};

// Latencies vs Request
function refreshLatenciesVsRequest() {
        var infos = latenciesVsRequestInfos;
        prepareSeries(infos.data);
        if(isGraph($("#flotLatenciesVsRequest"))){
            infos.createGraph();
        }else{
            var choiceContainer = $("#choicesLatencyVsRequest");
            createLegend(choiceContainer, infos);
            infos.createGraph();
            setGraphZoomable("#flotLatenciesVsRequest", "#overviewLatenciesVsRequest");
            $('#footerLatenciesVsRequest .legendColorBox > div').each(function(i){
                $(this).clone().prependTo(choiceContainer.find("li").eq(i));
            });
        }
};

var hitsPerSecondInfos = {
        data: {"result": {"minY": 7.166666666666667, "minX": 1.7500248E12, "maxY": 14.5, "series": [{"data": [[1.7500248E12, 14.5], [1.75002486E12, 7.166666666666667]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75002486E12, "title": "Hits Per Second"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of hits / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendHitsPerSecond"
                },
                selection: {
                    mode : 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y.2 hits/sec"
                }
            };
        },
        createGraph: function createGraph() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesHitsPerSecond"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotHitsPerSecond"), dataset, options);
            // setup overview
            $.plot($("#overviewHitsPerSecond"), dataset, prepareOverviewOptions(options));
        }
};

// Hits per second
function refreshHitsPerSecond(fixTimestamps) {
    var infos = hitsPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if (isGraph($("#flotHitsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesHitsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotHitsPerSecond", "#overviewHitsPerSecond");
        $('#footerHitsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var codesPerSecondInfos = {
        data: {"result": {"minY": 7.466666666666667, "minX": 1.7500248E12, "maxY": 14.2, "series": [{"data": [[1.7500248E12, 14.2], [1.75002486E12, 7.466666666666667]], "isOverall": false, "label": "200", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75002486E12, "title": "Codes Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendCodesPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "Number of Response Codes %s at %x was %y.2 responses / sec"
                }
            };
        },
    createGraph: function() {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesCodesPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotCodesPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewCodesPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Codes per second
function refreshCodesPerSecond(fixTimestamps) {
    var infos = codesPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotCodesPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesCodesPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotCodesPerSecond", "#overviewCodesPerSecond");
        $('#footerCodesPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var transactionsPerSecondInfos = {
        data: {"result": {"minY": 7.466666666666667, "minX": 1.7500248E12, "maxY": 14.2, "series": [{"data": [[1.7500248E12, 14.2], [1.75002486E12, 7.466666666666667]], "isOverall": false, "label": "HTTP Request 3-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75002486E12, "title": "Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTransactionsPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                }
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTransactionsPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTransactionsPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewTransactionsPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Transactions per second
function refreshTransactionsPerSecond(fixTimestamps) {
    var infos = transactionsPerSecondInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTransactionsPerSecond");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotTransactionsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTransactionsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTransactionsPerSecond", "#overviewTransactionsPerSecond");
        $('#footerTransactionsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var totalTPSInfos = {
        data: {"result": {"minY": 7.466666666666667, "minX": 1.7500248E12, "maxY": 14.2, "series": [{"data": [[1.7500248E12, 14.2], [1.75002486E12, 7.466666666666667]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75002486E12, "title": "Total Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTotalTPS"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                },
                colors: ["#9ACD32", "#FF6347"]
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTotalTPS"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTotalTPS"), dataset, options);
        // setup overview
        $.plot($("#overviewTotalTPS"), dataset, prepareOverviewOptions(options));
    }
};

// Total Transactions per second
function refreshTotalTPS(fixTimestamps) {
    var infos = totalTPSInfos;
    // We want to ignore seriesFilter
    prepareSeries(infos.data, false, true);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotTotalTPS"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTotalTPS");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTotalTPS", "#overviewTotalTPS");
        $('#footerTotalTPS .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

// Collapse the graph matching the specified DOM element depending the collapsed
// status
function collapse(elem, collapsed){
    if(collapsed){
        $(elem).parent().find(".fa-chevron-up").removeClass("fa-chevron-up").addClass("fa-chevron-down");
    } else {
        $(elem).parent().find(".fa-chevron-down").removeClass("fa-chevron-down").addClass("fa-chevron-up");
        if (elem.id == "bodyBytesThroughputOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshBytesThroughputOverTime(true);
            }
            document.location.href="#bytesThroughputOverTime";
        } else if (elem.id == "bodyLatenciesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesOverTime(true);
            }
            document.location.href="#latenciesOverTime";
        } else if (elem.id == "bodyCustomGraph") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCustomGraph(true);
            }
            document.location.href="#responseCustomGraph";
        } else if (elem.id == "bodyConnectTimeOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshConnectTimeOverTime(true);
            }
            document.location.href="#connectTimeOverTime";
        } else if (elem.id == "bodyResponseTimePercentilesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimePercentilesOverTime(true);
            }
            document.location.href="#responseTimePercentilesOverTime";
        } else if (elem.id == "bodyResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeDistribution();
            }
            document.location.href="#responseTimeDistribution" ;
        } else if (elem.id == "bodySyntheticResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshSyntheticResponseTimeDistribution();
            }
            document.location.href="#syntheticResponseTimeDistribution" ;
        } else if (elem.id == "bodyActiveThreadsOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshActiveThreadsOverTime(true);
            }
            document.location.href="#activeThreadsOverTime";
        } else if (elem.id == "bodyTimeVsThreads") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTimeVsThreads();
            }
            document.location.href="#timeVsThreads" ;
        } else if (elem.id == "bodyCodesPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCodesPerSecond(true);
            }
            document.location.href="#codesPerSecond";
        } else if (elem.id == "bodyTransactionsPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTransactionsPerSecond(true);
            }
            document.location.href="#transactionsPerSecond";
        } else if (elem.id == "bodyTotalTPS") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTotalTPS(true);
            }
            document.location.href="#totalTPS";
        } else if (elem.id == "bodyResponseTimeVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeVsRequest();
            }
            document.location.href="#responseTimeVsRequest";
        } else if (elem.id == "bodyLatenciesVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesVsRequest();
            }
            document.location.href="#latencyVsRequest";
        }
    }
}

/*
 * Activates or deactivates all series of the specified graph (represented by id parameter)
 * depending on checked argument.
 */
function toggleAll(id, checked){
    var placeholder = document.getElementById(id);

    var cases = $(placeholder).find(':checkbox');
    cases.prop('checked', checked);
    $(cases).parent().children().children().toggleClass("legend-disabled", !checked);

    var choiceContainer;
    if ( id == "choicesBytesThroughputOverTime"){
        choiceContainer = $("#choicesBytesThroughputOverTime");
        refreshBytesThroughputOverTime(false);
    } else if(id == "choicesResponseTimesOverTime"){
        choiceContainer = $("#choicesResponseTimesOverTime");
        refreshResponseTimeOverTime(false);
    }else if(id == "choicesResponseCustomGraph"){
        choiceContainer = $("#choicesResponseCustomGraph");
        refreshCustomGraph(false);
    } else if ( id == "choicesLatenciesOverTime"){
        choiceContainer = $("#choicesLatenciesOverTime");
        refreshLatenciesOverTime(false);
    } else if ( id == "choicesConnectTimeOverTime"){
        choiceContainer = $("#choicesConnectTimeOverTime");
        refreshConnectTimeOverTime(false);
    } else if ( id == "choicesResponseTimePercentilesOverTime"){
        choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        refreshResponseTimePercentilesOverTime(false);
    } else if ( id == "choicesResponseTimePercentiles"){
        choiceContainer = $("#choicesResponseTimePercentiles");
        refreshResponseTimePercentiles();
    } else if(id == "choicesActiveThreadsOverTime"){
        choiceContainer = $("#choicesActiveThreadsOverTime");
        refreshActiveThreadsOverTime(false);
    } else if ( id == "choicesTimeVsThreads"){
        choiceContainer = $("#choicesTimeVsThreads");
        refreshTimeVsThreads();
    } else if ( id == "choicesSyntheticResponseTimeDistribution"){
        choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        refreshSyntheticResponseTimeDistribution();
    } else if ( id == "choicesResponseTimeDistribution"){
        choiceContainer = $("#choicesResponseTimeDistribution");
        refreshResponseTimeDistribution();
    } else if ( id == "choicesHitsPerSecond"){
        choiceContainer = $("#choicesHitsPerSecond");
        refreshHitsPerSecond(false);
    } else if(id == "choicesCodesPerSecond"){
        choiceContainer = $("#choicesCodesPerSecond");
        refreshCodesPerSecond(false);
    } else if ( id == "choicesTransactionsPerSecond"){
        choiceContainer = $("#choicesTransactionsPerSecond");
        refreshTransactionsPerSecond(false);
    } else if ( id == "choicesTotalTPS"){
        choiceContainer = $("#choicesTotalTPS");
        refreshTotalTPS(false);
    } else if ( id == "choicesResponseTimeVsRequest"){
        choiceContainer = $("#choicesResponseTimeVsRequest");
        refreshResponseTimeVsRequest();
    } else if ( id == "choicesLatencyVsRequest"){
        choiceContainer = $("#choicesLatencyVsRequest");
        refreshLatenciesVsRequest();
    }
    var color = checked ? "black" : "#818181";
    if(choiceContainer != null) {
        choiceContainer.find("label").each(function(){
            this.style.color = color;
        });
    }
}

