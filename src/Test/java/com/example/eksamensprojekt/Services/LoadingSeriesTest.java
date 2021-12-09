package com.example.eksamensprojekt.Services;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoadingSeriesTest {

    /*
    @Test
    void tryParseToFloat() {
    }

     */

    @Test
    void openFile() {
        LoadingSeries ls = new LoadingSeries();
        ls.openFile();
        System.out.println(ls.series);
    }

    /*
    @Test
    void closeFile() {
    }

     */
}