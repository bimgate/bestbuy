package com.bestbuy.qa.helpers;

import java.util.ArrayList;

/**
 * Created by bratislav.miletic on 07/10/2017.
 */
public class Sorted {
   public boolean isSortedDescending(ArrayList<Double> list){
        boolean sorted = true;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1) >= (list.get(i)) ) {
                sorted = true;
            } else {
                return false;
            }
        }
        return sorted;
    }

    public boolean isSortedAscending(ArrayList<Double> list){
        boolean sorted = true;

        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                sorted =  false;
            }
        }
        return sorted;
    }
}
