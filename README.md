 # ServiceAIDL
 **app中（包含AppService）**
* 1、首先创建AppService 继承自Service类
* 2、重写类中对应的方法，在onBind()方法中进行如下的复写
  **IAppServiceRemoteBinder是已完成创建的AIDL文件，Stub及为其接口，其中的两个方法在文件中设置**

* 3、创建AILD文件：先创建Package包AIDL -> 创建 AIDL文件
**anotherapp中**
* 1、创建AIDL文件：创建Package包AIDL -> 创建 AIDL文件（通过复制app中的aidl文件）
* 2、创建活动，并包含一个**AIDL对象**，并实现ServiceConnection接口，并复写其中的抽象方法
* 3、创建Intent对象，并将对应的其他进程中的服务设置在此Intent对象内
* 4 、直接绑定对应的服务，通过mBinder对象即可操作AIDL接口设置的方法

