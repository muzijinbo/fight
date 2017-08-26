<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8"> 
	<title>Bootstrap 实例 - 垂直的胶囊式导航菜单</title>
	<link rel="stylesheet" href="../css/bootstrap.min.css">  
	    <link rel="stylesheet" href="../css/bootstrap-responsive.min.css" />
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	  <link rel="stylesheet" href="../css/matrix-style.css" />
      <link rel="stylesheet" href="../css/matrix-media.css" />
      
  <link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>
<script type="text/javascript">
$(document).ready(function() {
	$.ajax({
		type : "post",
		url : "../AgentServlet?method=listAgents",
		data : {
		},
		success : function(data) {
			var jdata = eval("("+data+")");
			for (var i = 10; i < jdata.length; i++) {
				$("#ullist").append("<li><a class='menu_a' link='NewFile.html?o_nbr="+jdata[i].id+"'><i class='icon icon-caret-right'></i>"+jdata[i].id+"</a></li>");
				//$("#ullist").append("<li><a class='menu_a' link='NewFile.html?o_nbr=O1030'>o1030</a></li>");

				}	
		}
	}); 
});  
/* $(function () {        	
	$.ajax({
		type : "post",
		url : "../AgentServlet?method=listAgents",
		data : {
		},
		success : function(data) {
			var jdata = eval("("+data+")");
			for (var i = 10; i < 10; i++) {
				$("#ullist").append("<li><a class='menu_a' link='NewFile.html?o_nbr="+jdata[i].id+"'><i class='icon icon-caret-right'></i>"+jdata[i].id+"</a></li>");
				//$("#ullist").append("<li><a class='menu_a' link='NewFile.html?o_nbr=O1030'>o1030</a></li>");

				}	
		}
	}); 
}); */
</script>
<body>
 
 <!--Header-part-->
   <div id="header">
       <h1><a href="dashboard.html">代理人画像</a></h1>
    </div>
    <!--sidebar-menu-->
    <div id="sidebar"  style="OVERFLOW-Y: auto; OVERFLOW-X:hidden;">
        <ul>
            <li class="submenu active"> 
                <a href="#">
                    <!-- <i class="icon icon-table"></i>  -->
                    <span>代理人</span> 
                    <span class="label label-important">1</span>
                </a>
               
				<ul class="nav nav-pills nav-stacked" id="ullist">
					<li><a class="menu_a" link="NewFile.html?o_nbr=O1005" ><i class="icon icon-caret-right"></i>O1005</a></li>
					<li><a class="menu_a" link="NewFile.html?o_nbr=O1007"><i class="icon icon-caret-right"></i>O1007</a></li>
					<li><a class="menu_a" link="NewFile.html?o_nbr=O1011"><i class="icon icon-caret-right"></i>O1011</a></li>
					<li><a class="menu_a" link="NewFile.html?o_nbr=O1039"><i class="icon icon-caret-right"></i>O1039</a></li>
					<li><a class="menu_a" link="NewFile.html?o_nbr=O1062"><i class="icon icon-caret-right"></i>O1062</a></li>
					<li><a class="menu_a" link="NewFile.html?o_nbr=O107" ><i class="icon icon-caret-right"></i>O107</a></li>
					<li><a class="menu_a" link="NewFile.html?o_nbr=O108"><i class="icon icon-caret-right"></i>O108</a></li>
					<li><a class="menu_a" link="NewFile.html?o_nbr=O117"><i class="icon icon-caret-right"></i>O117</a></li>
					<li><a class="menu_a" link="NewFile.html?o_nbr=O1143"><i class="icon icon-caret-right"></i>O1143</a></li>
					<li><a class="menu_a" link="NewFile.html?o_nbr=O1147"><i class="icon icon-caret-right"></i>O1147</a></li>
		
				
				</ul>
            </li>          
        </ul>
    </div>
    <!--sidebar-menu-->

    <!--main-container-part-->
    <div id="content">
        <iframe src="NewFile.html?o_nbr" id="iframe-main" frameborder='0' style="width:100%;"></iframe>
    </div>
    <!--end-main-container-part-->

    <script src="../js/excanvas.min.js"></script> 
    <script src="../js/jquery.min.js"></script> 
    <script src="../js/jquery.ui.custom.js"></script> 
    <script src="../js/bootstrap.min.js"></script> 
    <script src="../js/nicescroll/jquery.nicescroll.min.js"></script> 
    <script src="../js/matrix.js"></script> 


    <script type="text/javascript">

    //初始化相关元素高度
    function init(){
        $("body").height($(window).height()-80);
        $("#iframe-main").height($(window).height());
        $("#sidebar").height($(window).height()-50);
    }

    $(function(){
        init();
        $(window).resize(function(){
            init();
        });
    });

    // This function is called from the pop-up menus to transfer to
    // a different page. Ignore if the value returned is a null string:
    function goPage (newURL) {
        // if url is empty, skip the menu dividers and reset the menu selection to default
        if (newURL != "") {
            // if url is "-", it is this page -- reset the menu:
            if (newURL == "-" ) {
                resetMenu();            
            } 
            // else, send page to designated URL            
            else {  
                document.location.href = newURL;
            }
        }
    }

    // resets the menu selection upon entry to this page:
    function resetMenu() {
        document.gomenu.selector.selectedIndex = 2;
    }

    // uniform使用示例：
    // $.uniform.update($(this).attr("checked", true));
    </script>
</body>
</html>