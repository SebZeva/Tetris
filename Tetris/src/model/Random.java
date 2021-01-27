package model;

/*
 * The MIT License
 *
 * Copyright 2020 Sebastián Zevallos.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


/**
 *
 * @author Sebastián Zevallos
 */
public class Random
{
    /**
     * Generates a random int within specified boundaries.
     * 
     * @param min minimum int this function may generate.
     * @param max maximum int this function can generate.
     * @return generated int.
     */
    public static int randomInt(int min, int max)
    {
        double range = (double) (max - min + 1);
        return min + (int) (Math.random() * range);
    }
    
    /**
     * Generates a random name of random length; the length may be four, six
     * or eight; the name starts with an uppercase consonant and then alternates
     * between a vowel and a consonant until it reaches generated length.
     * 
     * @return generated name.
     */
    public static String randomName()
    {
        String izen = "";
        boolean exit = false;
        char toAdd = 'a';
        while (!exit)
        {
            toAdd = (char)randomInt(66, 90);
            switch (toAdd)
            {
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                    continue;
                default:
                    exit = true;
            }
        }
        izen += toAdd;
        int end = randomInt(1, 3);
        int i = 0;
        while (true)
        {
            switch (randomInt(1, 5))
            {
                case 1:
                    izen += 'a';
                    break;
                case 2:
                    izen += 'e';
                    break;
                case 3:
                    izen += 'i';
                    break;
                case 4:
                    izen += 'o';
                    break;
                case 5:
                    izen += 'u';
                    break;
                default:
                    izen += 'a';
            }
            if (i >= end)
                break;
            exit = false;
            while (!exit)
            {
                toAdd = (char)randomInt(98, 122);
                switch (toAdd)
                {
                    case 'e':
                    case 'i':
                    case 'o':
                    case 'u':
                        continue;
                    default:
                        exit = true;
                }
            }
            ++i;
            izen += toAdd;
        }
        return izen;
    }

    public static String randomString(int len)
    {
        String ret = "";
        for (int i = 0; i < len; ++i)
            ret += (char) randomInt(65, 90);
        return ret;
    }
}
