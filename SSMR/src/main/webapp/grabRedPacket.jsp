<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2020-08-30
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>参数</title>
    <script type="text/javascript" src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            //模拟30000个异步请求，进行并发
            var max = 3;
            for(var i=1; i<max; i++){
                $.post({
                   url:"./userRedPacket/grabRedPacket.do?redPacketId=1&userId="+i,
                   //  url:"./userRedPacket/grabRedPacketByRedis.do?redPacketId=5&userId="+i,
                   success:function (result) {
                       // console.log(result);
                   }
                });
            }
        })
    </script>
</head>
<body>

</body>
</html>
