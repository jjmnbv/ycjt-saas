package com.beitu.saas.risk.helpers;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.matchprocessor.ClassMatchProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/5/11
 * Time: 下午5:29
 */
public class ClassUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     *
     * @return
     */
    public static ClassLoader getClassLodader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     *
     * @param className
     * @param isInitialized
     * @return
     */
    public static Class<?> loadClass(String className, Boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLodader());
        } catch (ClassNotFoundException e) {
            logger.error("load class failure", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 获取指定包名下的所有类
     *
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        final Set<Class<?>> classes = new HashSet<>();
        //包名转换成文件目录地址 举例com.alibaba.taobao 转换为 com/alibaba/taobao
        try {
//            Enumeration<URL> urls = getClassLodader().getResources(packageName.replace(".", "/"));
//            while (urls.hasMoreElements()) {
//                URL url = urls.nextElement();
//                if (null != url) {
//                    System.err.println(url.getPath());
//                    String protocol = url.getProtocol();
//                    if (protocol.equals("file")) {
//                        String packagePath = url.getPath().replaceAll("%20", "");//去空格
//                        addClass(classSet, packagePath, packageName);
//                    } else if (protocol.equals("jar")) {
//                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
//                        if (null != jarURLConnection) {
//                            JarFile jarFile = jarURLConnection.getJarFile();
//                            Enumeration<JarEntry> jarEntries = jarFile.entries();
//                            while (jarEntries.hasMoreElements()) {
//                                JarEntry jarEntry = jarEntries.nextElement();
//                                String jarEntryName = jarEntry.getName();
//                                if (jarEntryName.endsWith(".class")) {
//                                    String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
//                                    doAddClass(classSet, className);
//                                }
//                            }
//                        }
//                    }
//                }
//            }

            new FastClasspathScanner(packageName)
                    .matchAllClasses(new ClassMatchProcessor() {
                        @Override
                        public void processMatch(Class<?> klass) {
                            classes.add(klass);
                        }
                    }).scan();

        } catch (Exception e) {
            logger.error("get class set failure", e);
        }
        return classes;
    }


    public static Set<String> getCurrentClassNames(String packageName) {
        Set<Class<?>> classSet = ClassUtil.getClassSet(packageName);

        Set<String> classNames = new HashSet<>();
        for (Class<?> c : classSet) {
            String className = c.getName();
            if (!className.contains(packageName)) {
                continue;
            }

            String[] packageSplit = packageName.split("\\.");
            String[] classNameSplit = className.split("\\.");
            if (classNameSplit.length - packageSplit.length != 1) {
                continue;
            }

            classNames.add(c.getName());
        }

        return classNames;

    }

    public static Set<String> getAllClassNames(String packageName) {
        Set<Class<?>> classSet = ClassUtil.getClassSet(packageName);

        Set<String> classNames = new HashSet<>();
        for (Class<?> c : classSet) {
            classNames.add(c.getName());
        }

        return classNames;

    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {

        final File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class") || file.isDirectory());
            }
        });
        for (File f : files) {//遍历当前的目录的所有文件如果是class文件则直接放入set中，否则继续遍历
            String name = f.getName();
            if (f.isFile()) {
                String className = name.substring(0, name.lastIndexOf("."));
                if (StringUtils.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {//递归调用一直到文件目录最后一级
                String subPackagePath = name;
                if (StringUtils.isNotEmpty(subPackagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = name;
                if (StringUtils.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }

}
