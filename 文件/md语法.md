一、基本符号：* - +. >
基本上所有的markdown标记都是基于这四个符号或组合，需要注意的是，如果以基本符号开头的标记，注意基本符号后有一个用于分割标记符和内容的空格。

1.前面带#号，后面带文字，分别表示h1-h6,只到h6，而且h1下面会有一条横线
# 一级标题 #
## 二级标题
### 三级标题
#### 四级标题
##### 五级标题
###### 六级标题

###形式一
+ a
+ b
+ c
###形式二
- d
- e
- f
###形式三
* g
* h
* i

> 引用内容、说明内容。在语句前面加一个 > ，注意是英文的那个右尖括号，注意空格，引用因为是一个区块，理论上是应该什么内容都可以放，比如说：标题，列表，引用等等。

> 一级引用
>> 二级引用
>>> 三级引用
>>>> 四级引用
>>>>> 五级引用
>>>>>> 六级引用

` shaoliangdaima,danhangshiyong `

```
    daliangdaima,xuyaoduohangshiyong
    daliangdaima,xuyaoduohangshiyong
```

[简书](https://www.jianshu.com "创作你的创作"),
是一个创作社区,任何人均可以在其上进行创作。用户在简书上面可以方便的创作自己的作品,互相交流。 

[简书]: https://www.jianshu.com "创作你的创作"
[简书]是一个创作社区,任何人均可以在其上进行创作。用户在简书上面可以方便的创作自己的作品,互相交流。
//参数定义的其他写法
[简书]: https://www.jianshu.com '创作你的创作'
[简书]: https://www.jianshu.com (创作你的创作)
[简书]: <https://www.jianshu.com> "创作你的创作"

![my-logo.png](https://upload-images.jianshu.io/upload_images/13623636-6d878e3d3ef63825.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240 "my-logo")

---
- - -
------
***
* * *
******
___
_ _ _
______

*md*    
**md**
_md_   
 __md__
 
 ~~删除~~
 
 //例子一
 |123|234|345|
 |:-|:-:|-:|
 |abc|bcd|cde|
 |abc|bcd|cde|
 |abc|bcd|cde|
 //例子二
 |123|234|345|
 |:---|:---:|---:|
 |abc|bcd|cde|
 |abc|bcd|cde|
 |abc|bcd|cde|
 //例子三
 123|234|345
 :-|:-:|-:
 abc|bcd|cde
 abc|bcd|cde
 abc|bcd|cde