1.生成datas/目录下文件时,出现某些记录占据两行或以上,导致Analyze类读取该文件进行分析时将一条记录当多条记录处理。解决办法是替换掉记录内容中的换行符(char类型,值为10),替换过程中出现问题:使用String类的replace方法,该方法在替换掉字符后返回的是一个新的String对象,要注意要使用新String对象的引用,不然看起来会好像没有发生替换动作。
2.Sevlet在返回response里的tdcs记录时没有正常显示中文,解决方法是调用response.setContentType("text/html charset=utf-8");
3.在前端页面按表单选择月份时(例如,1),服务器选择的数据文件是11月份的,原因是选择月份对应文件的判断依据是数据文件名是否包含
表单选择的月份。
  for(file:directory.listFiles()){
     if(fileName.contains(month)) {
       //statements 
     }
  }   
   由于是遍历文件,在contains判断正确后并没有马上break跳出循环,导致继续往下个文件判断,此情况出现在表单月份选择为1或2时,服务器最终选择的是11月份或12月份。
   解决方法:
   1.判断contains成立后执行break语句;
   2.修改服务器选择数据文件的判断语句,修改为
   if(month.equals(fileName.substring(fileName.indexOf("年")+1,fileName.indexOf("月")))){ 	//statements 
   }
4.使用索引进行不良信息检索,list postings使用Arrays.sort进行排序。
5.增加数据库操作,将result目录下文档数据写入数据库test表records。
6.更新TdcsTask类中doSearch方法,改进多关键字搜索逻辑。
伪代码:
遍历所有文件;遍历文件中每行;判断每行是否同时包含所有关键字;满足条件的行存入结果集。