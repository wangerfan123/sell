# 记录在学习过程中所遇见的问题以及收获

### 1.项目架构说明
        
        1.DTO是web层与服务层之间传递数据的对象
        2.VO代表web层需要显示的数据
        3.pojo不包含业务逻辑的单纯用来存储数据的 java类。一般POJO没有实现任何接口,
          没有集成任何父类(java.lang.Object除外)。

            
### 2.异常处理说明
        1.定义枚举类，在枚举类中设置错误信息和错误代码，设置get方法
        2.自定义一个异常，在异常中只定义一个有参构造，并传入该枚举类，获取枚举类中错误信息及代码
        
### 3.注解
        @Valid : 在实体的属性上添加校验规则，在API接收数据时添加@Valid注解，这时你的实体将会开启一个校验的功能。
        Spring验证的错误返回  ·····>  BindingResult 
        @Valid 和 BindingResult 是一一对应的，如果有多个@Valid，
         那么每个@Valid后面跟着的BindingResult就是这个@Valid的验证结果，顺序不能乱

### 4.Gson
    Gson gson = new Gson();
    gson.fromJson() //反序列化 :是指把字节序列恢复为对象的过程
    gson.toJson()   //序列化:是指把对象转换为字节序列的过程
    
    https://www.cnblogs.com/qinxu/p/9504412.html
    

### 5.JPA和Page实现分页
    Page: Spring Data提供的一个接口，该接口表示一部分数据的集合以及其相关的下一部分数据、数据总数等相关信息，
    通过该接口，我们可以得到数据的总体信息（数据总数、总页数...）以及当前数据的信息（当前数据的集合、当前页数等）
    List<T> getContent();     //将所有数据返回为List  
      long getTotalElements();    //返回元素总数。  
    
    Pageable :Pageable 是Spring Data库中定义的一个接口，该接口是所有分页相关信息的一个抽象，通过该接口，
    我们可以得到和分页相关所有信息（例如pageNumber、pageSize等），这样，Jpa就能够通过pageable参数来得到一个带分页信息的Sql语句。
              
      Pageable定义了很多方法，但其核心的信息只有两个：

       一是分页的信息（page、size）
       二是排序的信息。Spring Data Jpa提供了PageRequest的具体实现
       
       
       https://blog.csdn.net/qq_39459412/article/details/79935564