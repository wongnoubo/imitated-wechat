# Andriod仿造微信主界面

<a name="62f880e1"></a>
## 1 工程概况
<a name="5368077f"></a>
### 1.1 工程相关
* android sdk tools:26.1.1
* Build tools Version:28.0.3
* gradle :2.3.3

<a name="50b9c2b1"></a>
### 1.2 工程概述
       本工程由一个活动，动态添加四个碎片实现。需要准备四组图标，表示没有被点击和正常点击或者滑动变色。本文所需的图标都是从[阿里巴巴矢量图标库下载](https://www.iconfont.cn/)。变色的图标经过ps的魔棒工具简单处理，再次调色即可。

<a name="0725b7c1"></a>
## 2 活动动态添加四个碎片
<a name="2acc24eb"></a>
### 2.1 布局文件
<FrameLayout    <br />android:id="@+id/address_layout"    <br />android:layout_width="0dp"    <br />android:layout_height="0dp"   <br /> android:layout_weight="0"><br /></FrameLayout>

FrameLayout是android中最简单的一种布局，这里用来加入随便很合适。本工程放在bottom.xml里面。

<a name="acca059a"></a>
### 2.2主活动继承Fragment的OnFragmentInteractionListener
* 本文是一个活动加载多个Fragment，所以活动需要继承多个OnFragmentInteractionListener。继承之后需要重写onFragmentInteraction方法。

`@Override`<br />`public void onFragmentInteraction(Uri uri) {}`

* 编写动态切换Fragment函数

本工程是replaceAddressFragment方法。<br />private void replaceAddressFragment(Fragment fragment){<br />      FragmentManager fragmentManager = getSupportFragmentManager();    <br />FragmentTransaction transaction = fragmentManager.beginTransaction();                            transaction.replace(R.id._address_layout_,fragment);    <br />      transaction.commit();<br />}


<a name="ddeb591c"></a>
### 2.3Fragment重写方法
* Fragment类里面重写onActivityCreated方法。里面可以加自己的逻辑，本工程是注册了一个点击事件。也可以在里面新建逻辑线程，这样不会阻塞UI线程。

<a name="3c01567b"></a>
## 3 滑动翻页和点击翻页逻辑
<a name="5ef2ccd4"></a>
### 3.1 滑动翻页
* 滑动翻页是通过ViewPager.getCurrentItem()方法获取当前滑动页面，然后再通过ImageButton按键实例setImageResource方法加载我们加过颜色的按键，显示处于当前页面的效果。本工程对应的方法是

onPageSelected。

<a name="becb5596"></a>
### 3.2 点击翻页
* 点击翻页是通过View.getId()获取当前点击页面，再加载加过颜色的按键，显示点击的效果。本工程是onClick()

<a name="e6a0ca8b"></a>
## 4 效果

![default.png](https://github.com/wongnoubo/imitated-wechat/blob/master/github-images/default.png)<br />仿造微信，加载工程完成后默认是首页。<br />
![test.gif](https://github.com/wongnoubo/imitated-wechat/blob/master/github-images/test.gif)<br />测试效果<br />
![log.png](https://github.com/wongnoubo/imitated-wechat/blob/master/github-images/log.png)<br />点击Fragment里面的按键，会有对应的日志打印，说明按键已经被调用<br />

<a name="6aa73519"></a>
## 5 [demo地址](https://github.com/wongnoubo/imitated-wechat)
有问题欢迎提issues，欢迎star~

