# 代码目录

本工程主要针对JVM垃圾回收和性能监控，提供的测试代码。模拟了常见的内存溢出情况和死锁问题等。

## 内存溢出代码示例

- DirectMemoryOOM.java

  直接内存区溢出：抛出 OOM,但是在 Heap Dump 文件中不会看见明显异常

- HeapOOM.java

  Java堆溢出：java.lang.OutOfMemoryError: Java heap space

- JavaVMStackOOM.java

  虚拟机在扩展栈时无法申请到足够的内存空间：java.lang.OutOfMemoryError: unable to create new native thread

- JavaVMStackSOF.java

  线程请求的栈深度大于虚拟机栈允许的最大深度，抛出 StackOverflowError 异常

## JVM监控工具代码示例

- OOMObject.java

  - 模拟 Java 内存占用不断增长：创建一个List不断往里添加对象。

  - 用 jconsole 监控内存占用曲线

- BusyThread.java

  - 模拟线程等待、线程死锁

  - 用jstack定位线程死循环和线程等待

- ReferenceCountingGC.java

  - 模拟对象循环引用
  - 启动参数添加 -XX:+PrintGCDetails 可以输出 gc 详细信息

- SynAddRunnable.java

  - 模拟线程死锁
  - jstack定位线程死锁



# JVM性能监控和故障处理工具

本章节对JVM监控的示例代码，记录其监控过程和日志分析。

## BusyThread.java

**代码功能简述：**

- main 线程创建 busyThread 和 lockThread 之后，等待20s，再结束线程

- 死循环线程：busyThread = createBusyThread();

- 等待状态的线程：lockThread = createLockThread(obj);

**jstack定位线程死循环和线程等待：**

- main线程：处于 TIMED_WAITING 状态，从 jstack 信息中可以看出，代码66行调用 Thread.sleep()导致线程挂起。
- testBusyThread：处于 RUNNABLE状态，由于死循环执行i++，CPU占用率最高（cpu=7734.38ms）。
- testLockThread：处于 WAITING 状态。从 jstack 信息中可以看出，代码第49行调用wait方法，导致线程处于等待状态，对应的 synchronized 锁为 <0x0000000712403e18> (a java.lang.Object)。

```sh
C:\Users\g00475762>jps
13600 BusyThread
16036 Jps
16040 Launcher
7192

C:\Users\g00475762>jstack 13600

"main" #1 prio=5 os_prio=0 cpu=78.13ms elapsed=8.01s tid=0x00000208235d51d0 nid=0x1010 waiting on condition  [0x000000aaa0fff000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(java.base@15.0.2/Native Method)
        at com.susu.study.jvm.BusyThread.main(BusyThread.java:66)

"testBusyThread" #15 prio=5 os_prio=0 cpu=7734.38ms elapsed=7.93s tid=0x00000208235d6b90 nid=0x2794 runnable  [0x000000aaa22fe000]
   java.lang.Thread.State: RUNNABLE
        at com.susu.study.jvm.BusyThread$1.run(BusyThread.java:27)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"testLockThread" #16 prio=5 os_prio=0 cpu=0.00ms elapsed=7.93s tid=0x00000208476e3070 nid=0x3f08 in Object.wait()  [0x000000aaa23fe000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@15.0.2/Native Method)
        - waiting on <0x0000000712403e18> (a java.lang.Object)
        at java.lang.Object.wait(java.base@15.0.2/Object.java:321)
        at com.susu.study.jvm.BusyThread$2.run(BusyThread.java:49)
        - locked <0x0000000712403e18> (a java.lang.Object)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)
```



## SynAddRunnable.java

线程死锁程序，非必现，需要多跑几次

**jstack定位线程死锁**

- ThreadB_2——ThreadB_9、ThreadA_3——ThreadA_9。这些线程处于BLOCKED状态，说明在等待synchronized锁。
- Found one Java-level deadlock：ThreadB_2、ThreadA_3，可以看出这两个线程互相持有对方等待的锁。

```sh
C:\Users\g00475762>jps
5524 SynAddRunnable
14552 Launcher
232 Jps
7192

C:\Users\g00475762>jstack 5524

"ThreadB_2" #20 prio=5 os_prio=0 cpu=0.00ms elapsed=12.56s tid=0x00000282f5a9e650 nid=0x3b64 waiting for monitor entry  [0x000000fed09ff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:20)
        - waiting to lock <0x00000007126454d8> (a java.lang.Integer)
        - locked <0x00000007126454e8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"ThreadA_3" #21 prio=5 os_prio=0 cpu=0.00ms elapsed=12.56s tid=0x00000282f5a9eb20 nid=0x2d34 waiting for monitor entry  [0x000000fed0aff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:20)
        - waiting to lock <0x00000007126454e8> (a java.lang.Integer)
        - locked <0x00000007126454d8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"ThreadA_4" #23 prio=5 os_prio=0 cpu=0.00ms elapsed=12.56s tid=0x00000282f5a9f810 nid=0x3448 waiting for monitor entry  [0x000000fed07fe000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:19)
        - waiting to lock <0x00000007126454d8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"ThreadB_4" #24 prio=5 os_prio=0 cpu=0.00ms elapsed=12.56s tid=0x00000282f5aa0d10 nid=0x30a0 waiting for monitor entry  [0x000000fed08ff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:19)
        - waiting to lock <0x00000007126454e8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"ThreadA_5" #25 prio=5 os_prio=0 cpu=0.00ms elapsed=12.56s tid=0x00000282f5aa11e0 nid=0x3990 waiting for monitor entry  [0x000000fed0bff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:19)
        - waiting to lock <0x00000007126454d8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"ThreadB_5" #26 prio=5 os_prio=0 cpu=0.00ms elapsed=12.56s tid=0x00000282f5aa16b0 nid=0x3454 waiting for monitor entry  [0x000000fed05ff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:19)
        - waiting to lock <0x00000007126454e8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"ThreadA_6" #27 prio=5 os_prio=0 cpu=0.00ms elapsed=12.56s tid=0x00000282f5aa1f90 nid=0x345c waiting for monitor entry  [0x000000fed06ff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:19)
        - waiting to lock <0x00000007126454d8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"ThreadB_6" #28 prio=5 os_prio=0 cpu=0.00ms elapsed=12.56s tid=0x00000282f5aa2510 nid=0xa38 waiting for monitor entry  [0x000000fed0cfe000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:19)
        - waiting to lock <0x00000007126454e8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"ThreadA_7" #29 prio=5 os_prio=0 cpu=0.00ms elapsed=12.55s tid=0x00000282f5aab820 nid=0x388 waiting for monitor entry  [0x000000fed0dfe000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:19)
        - waiting to lock <0x00000007126454d8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"ThreadB_7" #30 prio=5 os_prio=0 cpu=0.00ms elapsed=12.55s tid=0x00000282f5aaae80 nid=0x3414 waiting for monitor entry  [0x000000fed0efe000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:19)
        - waiting to lock <0x00000007126454e8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"ThreadA_8" #31 prio=5 os_prio=0 cpu=0.00ms elapsed=12.55s tid=0x00000282f5aab350 nid=0x2fb0 waiting for monitor entry  [0x000000fed0ffe000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:19)
        - waiting to lock <0x00000007126454d8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"ThreadB_8" #32 prio=5 os_prio=0 cpu=0.00ms elapsed=12.55s tid=0x00000282f5aad030 nid=0x2134 waiting for monitor entry  [0x000000fed10ff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:19)
        - waiting to lock <0x00000007126454e8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"ThreadA_9" #33 prio=5 os_prio=0 cpu=0.00ms elapsed=12.55s tid=0x00000282f5aaa9b0 nid=0xc90 waiting for monitor entry  [0x000000fed11ff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:19)
        - waiting to lock <0x00000007126454d8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

"ThreadB_9" #34 prio=5 os_prio=0 cpu=0.00ms elapsed=12.55s tid=0x00000282f5aa9b40 nid=0x28bc waiting for monitor entry  [0x000000fed12ff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:19)
        - waiting to lock <0x00000007126454e8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)
        

Found one Java-level deadlock:
=============================
"ThreadB_2":
  waiting to lock monitor 0x00000282f583aa80 (object 0x00000007126454d8, a java.lang.Integer),
  which is held by "ThreadA_3"

"ThreadA_3":
  waiting to lock monitor 0x00000282f583c880 (object 0x00000007126454e8, a java.lang.Integer),
  which is held by "ThreadB_2"

Java stack information for the threads listed above:
===================================================
"ThreadB_2":
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:20)
        - waiting to lock <0x00000007126454d8> (a java.lang.Integer)
        - locked <0x00000007126454e8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)
"ThreadA_3":
        at com.susu.study.jvm.SynAddRunnable.run(SynAddRunnable.java:20)
        - waiting to lock <0x00000007126454e8> (a java.lang.Integer)
        - locked <0x00000007126454d8> (a java.lang.Integer)
        at java.lang.Thread.run(java.base@15.0.2/Thread.java:832)

Found 1 deadlock.
```



## ReferenceCountingGC

-XX:+PrintGCDetails 输出 gc 日志

程序是循环引用的情况下，打印具体gc过程

```sh
[0.003s][warning][gc] -XX:+PrintGCDetails is deprecated. Will use -Xlog:gc* instead.
[0.017s][info   ][gc] Using G1
[0.019s][info   ][gc,init] Version: 15.0.2+7-27 (release)
[0.019s][info   ][gc,init] CPUs: 8 total, 8 available
[0.019s][info   ][gc,init] Memory: 16212M
[0.019s][info   ][gc,init] Large Page Support: Disabled
[0.019s][info   ][gc,init] NUMA Support: Disabled
[0.019s][info   ][gc,init] Compressed Oops: Enabled (Zero based)
[0.019s][info   ][gc,init] Heap Region Size: 2M
[0.019s][info   ][gc,init] Heap Min Capacity: 8M
[0.019s][info   ][gc,init] Heap Initial Capacity: 254M
[0.019s][info   ][gc,init] Heap Max Capacity: 4054M
[0.019s][info   ][gc,init] Pre-touch: Disabled
[0.019s][info   ][gc,init] Parallel Workers: 8
[0.019s][info   ][gc,init] Concurrent Workers: 2
[0.019s][info   ][gc,init] Concurrent Refinement Workers: 8
[0.019s][info   ][gc,init] Periodic GC: Disabled
[0.019s][info   ][gc,metaspace] CDS archive(s) mapped at: [0x0000000800000000-0x0000000800b50000-0x0000000800b50000), size 11862016, SharedBaseAddress: 0x0000000800000000, ArchiveRelocationMode: 0.
[0.019s][info   ][gc,metaspace] Compressed class space mapped at: 0x0000000800b50000-0x0000000840b50000, size: 1073741824
[0.019s][info   ][gc,metaspace] Narrow klass base: 0x0000000800000000, Narrow klass shift: 3, Narrow klass range: 0x100000000
[0.110s][info   ][gc,task     ] GC(0) Using 6 workers of 8 for full compaction
[0.110s][info   ][gc,start    ] GC(0) Pause Full (System.gc())
[0.110s][info   ][gc,phases,start] GC(0) Phase 1: Mark live objects
[0.111s][info   ][gc,phases      ] GC(0) Phase 1: Mark live objects 0.874ms
[0.111s][info   ][gc,phases,start] GC(0) Phase 2: Prepare for compaction
[0.112s][info   ][gc,phases      ] GC(0) Phase 2: Prepare for compaction 0.475ms
[0.112s][info   ][gc,phases,start] GC(0) Phase 3: Adjust pointers
[0.113s][info   ][gc,phases      ] GC(0) Phase 3: Adjust pointers 0.894ms
[0.113s][info   ][gc,phases,start] GC(0) Phase 4: Compact heap
[0.113s][info   ][gc,phases      ] GC(0) Phase 4: Compact heap 0.480ms
[0.114s][info   ][gc,heap        ] GC(0) Eden regions: 2->0(1)
[0.115s][info   ][gc,heap        ] GC(0) Survivor regions: 0->0(0)
[0.115s][info   ][gc,heap        ] GC(0) Old regions: 0->2
[0.115s][info   ][gc,heap        ] GC(0) Archive regions: 0->0
[0.115s][info   ][gc,heap        ] GC(0) Humongous regions: 4->0
[0.115s][info   ][gc,metaspace   ] GC(0) Metaspace: 634K(4864K)->634K(4864K) NonClass: 579K(4352K)->579K(4352K) Class: 54K(512K)->54K(512K)
[0.115s][info   ][gc             ] GC(0) Pause Full (System.gc()) 10M->0M(14M) 4.239ms
[0.115s][info   ][gc,cpu         ] GC(0) User=0.00s Sys=0.00s Real=0.01s
[0.115s][info   ][gc,heap,exit   ] Heap
[0.115s][info   ][gc,heap,exit   ]  garbage-first heap   total 14336K, used 929K [0x0000000702a00000, 0x0000000800000000)
[0.115s][info   ][gc,heap,exit   ]   region size 2048K, 1 young (2048K), 0 survivors (0K)
[0.115s][info   ][gc,heap,exit   ]  Metaspace       used 658K, capacity 4505K, committed 4864K, reserved 1056768K
[0.116s][info   ][gc,heap,exit   ]   class space    used 56K, capacity 391K, committed 512K, reserved 1048576K
```



# 拓展阅读

**Java常见的GC问题分析思路：**
https://tech.meituan.com/2020/11/12/java-9-cms-gc.html

**性能指标TP50、TP90、TP99、TP999：**

https://blog.csdn.net/qq_39809613/article/details/102853986?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control