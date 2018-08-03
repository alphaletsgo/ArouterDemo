# ARouterDemon

> 这是一个关于如何组件化的一个简单实现的demo

## 非组件架构的痛点

相信大家在开发中经常遇到以下情况，尤其是分层结构化的项目：
- 增减需求如要命
- 一个小需求要编译运行整个项目
- 功能测试和需求测试每次都要进行
- 灵活性差，业务耦合

这种剪不断理还乱的结构，无时无刻不在督促我们要想组件化的架构演进。

## 路由

![](/images/20170522212705133.png)

路由是完成组件化的关键，是各个业务的衔接的桥梁[路由与业务关系如上图所示]，所以我们首要解决的就是这问题，由于android系统其开放性的特点[如果允许的话可以在打开任何app的任意界面]。android中一个Activity就代表一个可视界面，其打开方式包含显式启动和隐式启动这两种，显示启动没有什么好说的，一般用在App内部，而另一种隐式常被用来app之间的调用。所以路由实现原理也就这两种了，目前开源社区的主流框架也基本上基于这两种了，但是一个框架在保证其实现能力的同时，大牛支持、维护、大厂后盾等这些因素也是我们在选型的时候必须要考虑的。综上因素我们找到了ARouter框架，完全覆盖了我们对组件化的需求，所以我们就使用它作为我们路由框架了。

## 工程结构

![](/images/2018-08-02-17-04-37.png)

前面我们已经了解到了，传统的app架构所拥有的痛点，为了解决这个痛点我们引入了组件化架构设计，如何来设计组件化结构呢？其实关于这一点Android已经为我们做好了铺设，那就是利用Android library和gradle，我们可以在创建的Android工程下再创建一个或多个library，这是众所周知的事了，所以如上图所示，我们需要设计好app的业务模块，为每一个业务模块创建一个library工程，形成如上图所示的结构，当然一个app一般会依赖一些第三方库，我们就可以使用gradle来管理这些依赖。向上面我们介绍的ARouter也是属于第三方库，像这样一类库是各个模块都可能需要的，那么我们就可以把这些库组成一个公共组件库来使用，以方便统一管理，这样我们就将业务和公共组件分开了，有了这样的结构后我们就可以分模块开发了，但是这里还有如下问题需要解决。

### 如何解决引用库冲突问题？

一个工程中管理多个模块必然会有一些库重复引用或者版本冲突等问题，那么我们如何解决呢？其解决方案主要分两步。
第一步，我们可以将各个模块引用的一些第三方库的版本号，在工程目录下的build.gradle文件中以常量的方式定义，然后在各个模块中引用这些常量。

```Groovy
ext {
    compileSdkVersion = 25
    buildToolsVersion = "25.0.2"
    minSdkVersion = 21
    targetSdkVersion = 25
}
```

第二步，我们将各个模块都会使用的一些第三方库提取出来，把他们放到单独的一个gradle文件中，一般我们将该文件放在项目的根目录下[与setting.gradle同级]，然后在各个模块中引用它。
比如我们放置第三方库的文件叫third.gradle

```Groovy
dependencies {
    testCompile 'junit:junit:4.12'
    implementation 'com.squareup.okhttp3:okhttp:3.5.0'
    implementation 'com.google.code.gson:gson:2.6.1'
    implementation 'cn.isif.alibs:alibs:2.0.0'
}
```

在各个组件中引用

```groovy
apply from: "../third.gradle"
```

## 如何独立开发调试一个模块呢？

我们知道独立的library要调试的话，必须将library作为一个应用才行，在gradle中将一个模块第一位库使用`apply plugin: 'com.android.library'`，一个应用则使用`apply plugin: 'com.android.application'`，所以我们解决方法就有了，我们可以在最外层的build.gradle文件中定义个一个isdebug常量，在每个模块中做一下判断：
```groovy
if (isdebug.boodlean()){
    apply plugin: 'com.android.application'
}else{
    apply plugin: 'com.android.library'
}
```

只是将模块定义为application还是不能运行，我们还需要在mainfest文件定义启动页面，这里就有一个问题了，如果我们不做任何配置的话我们需要在切换组件的时候来回改这个文件，这显然不是一种好的方式，这里我们就需要到我们的gradle文件中去指定项目的mainfest文件了。如：我们在main目录下创建一个debug文件，里面copy一份mainfest文件用于配置调试阶段。
在build.gradle文件中

```groovy
sourceSets {
        main {
            if (isDebug.toBoolean()) {
                manifest.srcFile 'src/main/debug/AndroidManifest.xml'
            } else {
                manifest.srcFile 'src/main/AndroidManifest.xml'
            }
        }
    }
```

这样我们就解决模块的独立调试与开发的问题了。

更多关于组件化实现的细节可以参考我们的[ARouterDemo](https://github.com/uncle404/ArouterDemo)。