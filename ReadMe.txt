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
7.优化单元格渲染器对单元格长宽的计算,实现内容自动换行。
8.优化搜索界面,整合SearchPanel类(标签、输入框、搜索按钮)和SimpleGUI类(生成frame实例使用简单布局管理SearchPanel中的组件及本类的表格组件)的功能,删除上述两个类并新增SearchFrame类利用网格组布局管理上述两个类中的组件,优化数据类的使用。
9.优化用户体验,对搜索输入框对象添加keyListener,回车后进行搜索。
10.增加ScanFile类提供scan方法对文档进行扫描。减少重复代码:读取文档并扫描每行文本。
11.增加JFrame,其中包含JTree显示分析报告文件列表、JEditPane显示分析报告内容。
12.增加DocExtractor类解析word 2003文件,增加Report类将文本数据通过序列化对象进行存储,由于BufferedImage类没有实现seriazable接口,故无法序列化,将word中的图片直接导出文件保存。
13.增加PDFExtractor类解析pdf文件,考虑到厂家对部分pdf文件进行加密,直接使用PdfToImage直接把整个pdf文档导出成图片。(PDDocument类的isEncrypted()方法判断文档是否加密;
 org.apache.pdfbox.util.PDFImageWriter类的writeImage()方法可将每页文档导出成图片)。
14.实际只有24个pdf文档,其中厂家进行加密的只有3篇,先使用第三方软件进行解密,然后对pdf文档进行序列化存储。
15.优化不良信息搜索结果,过滤重复记录,对要搜索的文档进行排序,从日期最新的文档开始搜索,然后用HashSet进行过滤。
16.优化SearchTableModel对象初始化参数,复用JTable对pdf文档进行搜索。
