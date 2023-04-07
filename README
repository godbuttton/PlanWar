# 飞机大战

自学完 Java SE  教程，网上找个项目练习下，选中了飞机大战

## 需求分析

UI素材找的网上的，只不过， 网上的是用python实现的，现在用Java实现类似的功能

github :  https://github.com/yangshangqi/The-Python-code-implements-aircraft-warfare

## 知识背景

Swing 是 Java 为图形界面应用开发提供的一组工具包，是 Java 基础类的一部分。组件都处于`java.awt.Window` `javax.swing.JComponent` 包下

顶层：JFrame、JDialog

中间容器：JPanel、JScrollPanel、JSplitPane、JTabledPanel、JlayeredPanel

特殊容器：JMenuBar、JToolBar、JPopupMenu、JinternalFrame （内部窗口）

基本组件:  JLabel、JButton、JRadioButton、JCheckBox、JToggleButton、JTextField、JPasswordField、	JTextArea、JComboBox、	JList、

JProgressBar、JSlider

选取器组件:JFileChooser	、JColorChooser	

实际都没用到相关组件   根据点击事件的坐标判断的

## 开发

需求分析

基础类 ：  背景图片 、位置，长、宽 ，主窗口句柄、绘制方法，

飞行类： 背景图片 、状态 、位置 、长、宽、 飞行速度，血量  ，销毁方法,主窗口句柄

玩家飞机： 继承飞行  

敌方普通飞机：继承飞行 、类的有效实体计数

敌方Boss飞机：继承飞行 

子弹：飞行类 实体

爆炸类: 爆炸效果

资源类： 音视频类

Utils类 ：

- 敌方飞机的集合、敌方子弹的集合、所有待渲染的集合
- 画玩家飞机爆炸动画
- 画敌方飞机爆炸动画
- 播放音效 爆炸 声音

MainWin：

- 渲染图片、文字
- 启动窗口
- 属性: 游戏状态、

多线程：

	- 画图
	- 播放音乐

功能列表

- 玩家飞机屏幕内随便移动，无法移动出超出屏幕范围，按空格键发出子弹
- 敌方小飞机 1s一个 随机出现在屏幕顶部位置，且垂直落下来
- 敌方boss 在飞机数量达到500个后出来，地方boss击败后 显示通过
- '敌方boss 支持血量计算
- 我方飞机和敌方飞机碰撞 显示我方飞机爆炸图片，且播放声音
- 子弹和敌方飞机碰撞，且敌方飞机如果为boss飞机 敌方飞机爆炸渲染，且播放声音
- 随机落下来伞包，捡到伞包炸弹，按B，炸弹爆炸，界面上所有飞机都触发爆炸，大boss掉5滴血






遇到问题：

1. 音乐播放回阻塞界面，改为多线程

2. 抽象出的爆炸类 ，爆炸结束后需要销毁对应的 爆炸物，但是爆炸是利用Timer. schedule，一帧一帧的绘制图片 ，形成动画。需要等动画执行完毕后，在执行对应的方法。 如果是JS ，直接函数回调即可，但是JAVA不行，又不想去把实体对象传入。应该有 更加好的方式处理，最后还是把this传导进去了
   - 好像可以用线程池 + furture + callable 后面再看
3. 由于是多线程播放完动画后销毁飞机的, 所以都去读取 和删除 飞机导致最后集合列表不一致，报遍历错误
   - ConcurrentModificationException这个错误 ，加对象锁未解决问题，可能某个敌方没加，后来直接改为了CopyOnWriteArrayList

4. Jframe 的timer未重置，导致重新开始游戏的时候报错
