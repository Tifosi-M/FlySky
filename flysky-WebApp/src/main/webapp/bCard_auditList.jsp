<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-

strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>足迹生活后台管理</title>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely 

valid -->
<link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript" src="scripts/jquery-1.3.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="scripts/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="scripts/jquery.wysiwyg.js"></script>
<!-- jQuery Datepicker Plugin -->
<script type="text/javascript" src="scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="scripts/jquery.date.js"></script>
</head>
<body>
<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  <div id="sidebar">
    <div id="sidebar-wrapper">
      <!-- Sidebar with logo and menu -->
      <h1 id="sidebar-title"><a href="#">Simpla Admin</a></h1>
      <!-- Logo (221px wide) -->
      <a href="#"><img id="logo" src="images/logo.png" alt="Simpla Admin logo" /></a>
      <!-- Sidebar Profile links -->
      
        <ul id="main-nav">
        <!-- Accordion Menu -->
        <li> <a href="#" class="nav-top-item">活动管理</a>
          <ul>
            <li><a href="activity_auditList.jsp">活动审核</a></li>
           
          </ul>
        </li>
        <li>
          <ul>
            
          </ul>
        </li>
        <li> <a href="#" class="nav-top-item">名片管理</a>
          <ul>
            <li><a href="bCard_auditList.jsp">名片审核</a></li>
          
          </ul>
        </li>
 
      </ul>

      <!-- End #messages -->
    </div>
  </div>
  <!-- End #sidebar -->
  <div id="main-content">

    <h2>足迹生活后台管理</h2>
    <p id="page-intro"></p>

    <!-- End .shortcut-buttons-set -->
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>名片列表</h3>
        <ul class="content-box-tabs">
         
        </ul>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
          <!-- This is the target div. id must match the href of this div's tab -->
          <div > 
          <div >审核状态：
          <select>
              <option value ="volvo">==请选择==</option>
             <option value ="volvo">已审核</option>
             <option value ="saab">未审核</option>
          </select>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a class="button" href="#"> 查   询  </a> </div>
          </div>
          <table>
            <thead>
              <tr>
                <th>名片名称</th>
                <th>联系方式</th>
                <th>微博</th>
                <th>QQ</th>
                <th>地址</th>
                <th>审核状态</th>
                <th>操作</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
                  
                  <div class="pagination"> <a href="#" title="First Page">&laquo;首页</a><a 

href="#" title="Previous Page">&laquo; 上一页</a> <a href="#" class="number" title="1">1</a> <a 

href="#" class="number" title="2">2</a> <a href="#" class="number current" title="3">3</a> <a 

href="#" class="number" title="4">4</a> <a href="#" title="Next Page">下一页 &raquo;</a><a href="#" 

title="Last Page">尾页&raquo;</a> </div>
                  <!-- End .pagination -->
                  <div class="clear"></div>
                </td>
              </tr>
            </tfoot>
            <tbody>
              <tr>
                <td>义务维修</td>
                <td>2014.4.27上午</td>
                <td>南区食堂</td>
                <td>零点装机协会</td>
                <td>零点装机协会</td>
                <td><a href="#" title="title">未审核</td>
                <td><a href="bCard_audit.jsp" >进入审核</td>
              </tr>
            <tr>
                <td>义务维修</td>
                <td>2014.4.27上午</td>
                <td>南区食堂</td>
                <td>零点装机协会</td>
                <td>零点装机协会</td>
                <td><a href="#" title="title">已审核</td>
                <td><a href="client_detail.jsp" >无</td>
              </tr>
             <tr>
                <td>义务维修</td>
                <td>2014.4.27上午</td>
                <td>南区食堂</td>
                <td>零点装机协会</td>
                <td>零点装机协会</td>
                <td><a href="#" title="title">已审核</td>
                <td><a href="client_detail.jsp" >无</td>
              </tr>
              <tr>
                <td>义务维修</td>
                <td>2014.4.27上午</td>
                <td>南区食堂</td>
                <td>零点装机协会</td>
                <td>零点装机协会</td>
                <td><a href="#" title="title">已审核</td>
                <td><a href="client_detail.jsp" >无</td>
              </tr>
             <tr>
                <td>义务维修</td>
                <td>2014.4.27上午</td>
                <td>南区食堂</td>
                <td>零点装机协会</td>
                <td>零点装机协会</td>
                <td><a href="#" title="title">已审核</td>
                <td><a href="client_detail.jsp" >无</td>
              </tr>
            <tr>
                <td>义务维修</td>
                <td>2014.4.27上午</td>
                <td>南区食堂</td>
                <td>零点装机协会</td>
                <td>零点装机协会</td>
                <td><a href="#" title="title">已审核</td>
                <td><a href="client_detail.jsp" >无</td>
              </tr>
            <tr>
                <td>义务维修</td>
                <td>2014.4.27上午</td>
                <td>南区食堂</td>
                <td>零点装机协会</td>
                <td>零点装机协会</td>
                <td><a href="#" title="title">已审核</td>
                <td><a href="client_detail.jsp" >无</td>
              </tr>
            <tr>
                <td>义务维修</td>
                <td>2014.4.27上午</td>
                <td>南区食堂</td>
                <td>零点装机协会</td>
                <td>零点装机协会</td>
                <td><a href="#" title="title">已审核</td>
                <td><a href="client_detail.jsp" >无</td>
              </tr>
            </tbody>
          </table>
        </div>
        <!-- End #tab1 -->
        <div class="tab-content" id="tab2">

        </div>
        <!-- End #tab2 -->
      </div>
      <!-- End .content-box-content -->
    </div>
 
  
    <div class="clear"></div>
    <!-- End Notifications -->
<div id="footer"><small>Copyright  2011级    软件学院  软件工程 </small> </div>
    <!-- End #footer -->
  </div>
  <!-- End #main-content -->
</div>
</body>
<!-- Download From www.exet.tk-->
</html>

