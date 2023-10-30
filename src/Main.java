
// TODO add Array of algo for UI

import App.App;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run();
    }
}

/*
compress tests:
    test 1#:
        input:
            cabracadabrarrarrad
        output:
            <0, 0, c>
            <0, 0, a>
            <0, 0, b>
            <0, 0, r>
            <3, 1, c>
            <2, 1, d>
            <7, 4, r>
            <3, 3, r>
            <11, 2, ً>

    test 2#:
        input:
            abracadabrad
        output:
            <0, 0, a>
            <0, 0, b>
            <0, 0, r>
            <3, 1, c>
            <2, 1, d>
            <7, 4, d>

    test 3#:
        input:
            ABAABABAABBBBBBBBBBBBA
        output:
            <0, 0, A>
            <0, 0, B>
            <2, 1, A>
            <3, 2, B>
            <5, 3, B>
            <2, 2, B>
            <5, 5, B>
            <14, 2, ً>
*/