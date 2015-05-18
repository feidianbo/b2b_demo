package demo.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhangbolun on 15/4/2.
 */



public class SortList<T> {

    public void Sort(List<T> list, final String method, SortType sort){
        Collections.sort(list, new Comparator<T>() {
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                    Method m1 = ((T) a).getClass().getMethod(method);
                    Method m2 = ((T) b).getClass().getMethod(method);
                    if (SortType.desc.compareTo(sort)==0)//倒序
                        ret = m2.invoke(((T) b)).toString().compareTo(m1.invoke(((T) a)).toString());
                    else//正序
                        ret = m1.invoke(((T) a)).toString().compareTo(m2.invoke(((T) b)).toString());
                } catch (NoSuchMethodException ne) {
                    System.out.println(ne);
                } catch (IllegalAccessException ie) {
                    System.out.println(ie);
                } catch (InvocationTargetException it) {
                    System.out.println(it);
                }
                return ret;
            }
        });
    }

    public void Sort(List<T> list, final String method1, SortType sort1,final String method2, SortType sort2){
        Collections.sort(list, new Comparator<T>() {
            public int compare(Object  a, Object b) {
                int ret = 0;
                try {
                    Method m11 = ((T) a).getClass().getMethod(method1);
                    Method m12 = ((T) b).getClass().getMethod(method1);
                    Method m21 = ((T) a).getClass().getMethod(method2);
                    Method m22 = ((T) b).getClass().getMethod(method2);
                    if (SortType.desc.compareTo(sort1)==0)//倒序
                        ret = m12.invoke(((T) b)).toString().compareTo(m11.invoke(((T) a)).toString());
                        if(ret==0){
                            if (SortType.desc.compareTo(sort2)==0){
                                ret = m22.invoke(((T) a)).toString().compareTo(m21.invoke(((T) b)).toString());
                            }else {
                                ret = m22.invoke(((T) b)).toString().compareTo(m21.invoke(((T) a)).toString());
                            }
                        }
                    else//正序
                        ret = m11.invoke(((T) a)).toString().compareTo(m12.invoke(((T) b)).toString());
                        if(ret==0){
                            if (SortType.desc.compareTo(sort2)==0){
                                ret = m21.invoke(((T) a)).toString().compareTo(m22.invoke(((T) b)).toString());
                            }else {
                                ret = m21.invoke(((T) b)).toString().compareTo(m22.invoke(((T) a)).toString());
                            }
                        }
                } catch (NoSuchMethodException ne) {
                    System.out.println(ne);
                } catch (IllegalAccessException ie) {
                    System.out.println(ie);
                } catch (InvocationTargetException it) {
                    System.out.println(it);
                }
                return ret;
            }
        });
    }
}
