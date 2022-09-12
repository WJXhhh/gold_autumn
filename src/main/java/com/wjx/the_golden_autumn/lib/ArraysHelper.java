package com.wjx.the_golden_autumn.lib;

import java.util.ArrayList;

public class ArraysHelper {
    public static ArraysHelper instance = new ArraysHelper();
    public boolean CompareElements(ArrayList<?> array1,ArrayList<?> array2){
        if (array1.size() == array2.size()) {
            ArrayList<Integer> b1 = new ArrayList<>();
            ArrayList<Integer> b2 = new ArrayList<>();
            int i1 = 0;
            int i2 = 0;
            int i2Same = 0;
            for (int i = 0; i1 < array1.size(); i++) {
                for (int j = 0; i2 < array2.size();j++){
                    if (array1.get(i1) == array2.get(i2)){
                        i2Same++;
                        b1.add(getSameElementsCount(array1,array1.get(i1)));
                        b2.add(getSameElementsCount(array2,array2.get(i2)));
                    }
                    i2++;
                }
                if (i2Same == 0){
                    return false;
                }
                i2Same = 0;
                i2 = 0;
                i1++;
            }
            return b1.equals(b2);

        }
        else {
            return false;
        }
    }
    public int getSameElementsCount(ArrayList<?> array,Object obj){ //调用前先用contain保证含有此元素
        int ObjCount = 0;
        for (Object obj1 : array){
            if (obj1 == obj){
                ObjCount++;
            }
        }
        return ObjCount;
    }
    public int[] getSameElementsIndexes(ArrayList<?> array,Object obj){
        ArrayList<Integer> Indexes = new ArrayList<>();
        for(int i = 0 ; i < array.size();i++){
            if (array.get(i) == obj){
                Indexes.add(i);
            }
        }
        return Indexes.stream().mapToInt(k -> k).toArray();
    }
}
