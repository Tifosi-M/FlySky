﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
     
      <!-- Logo (221px wide) -->
      <a href="#"><img id="logo" src="images/logo.png"  /></a>
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
      <!-- End #main-nav -->

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
        <h3>审核校园活动</h3>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
          <form action="#" method="post">
            <fieldset> 
            <p>名称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input class="text-input small-input" type="text" id="small-input" name="small-input" />
            </p>
            <p>地点：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <input class="text-input small-input" type="text" id="medium-input" name="medium-input" />
            <span class="input-notification success png_bg"></span><br />
            </p>
             <p>时间：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input class="text-input small-input" type="text" id="medium-input" name="medium-input" />
            <span class="input-notification error png_bg"></span> </p>
             <p>发起人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input class="text-input small-input" type="text" id="medium-input" name="medium-input" />
            <span class="input-notification error png_bg"></span> </p>
            <p>内容：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <textarea class="text-input textarea wysiwyg" id="textarea" name="textfield" 
            cols="79" rows="3"></textarea>
            </p>
             <p>审核：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <select>
              <option value ="volvo">==请选择==</option>
             <option value ="volvo">通过</option>
             <option value ="saab">不通过</option>
          </select></p>
           </br>
              <input class="button" type="submit" value="提  交" />
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <input class="button" type="reset" value="返  回" />
            </fieldset>
          </form>
         
        </div>
        <!-- End #tab1 -->
        <div class="tab-content" id="tab2">
          
        </div>     
      </div>   
    </div>

    <!-- End Notifications -->
    <div id="footer"><small> Copyright  2011级    软件学院  软件工程 </small> </div>
  </div>
  <!-- End #main-content -->
</div> 
</body>

</html>

