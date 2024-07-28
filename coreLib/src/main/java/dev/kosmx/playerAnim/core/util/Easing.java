package dev.kosmx.playerAnim.core.util;


import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Easing {

    /**
     * Easing functions from easings.net
     * All function have a string codename
     * EasingFromString
     * <p>
     * All function needs an input between 0 and 1
     *
     * @deprecated Just use {@link Ease#invoke(float)}
     */
    @Deprecated
    public static float easingFromEnum(@Nullable Ease type, float f) {
        return type != null ? type.invoke(f) : f;
    }

    /**
     * @param string ease name
     * @return ease
     */
    public static Ease easeFromString(String string){
        try{
            if(string.equals("step"))return Ease.CONSTANT;
            if(string.substring(0, 4).equalsIgnoreCase("EASE")){
                string = string.substring(4);
            }
            return Ease.valueOf(string.toUpperCase());
        } catch(Exception exception){
            //Main.log(Level.ERROR, "Ease name unknown: \"" + string + "\" using linear", true);
            //Main.log(Level.WARN, exception.toString());
            return Ease.LINEAR;
        }
    }

    private static final float c1 = 1.70158f;
    private static final float c2 = c1 * 1.525f;
    private static final float c3 = c1 + 1;
    private static final float c4 = (float) ((2 * Math.PI) / 3);
    private static final float c5 = (float) ((2 * Math.PI) / 4.5);
    private static final float n1 = 7.5625f;
    private static final float d1 = 2.75f;


    public static float inSine(float f){
        return (float) (1 - Math.cos((f * Math.PI) / 2));
    }

    public static float outSine(float f){
        return (float) (Math.sin((f * Math.PI) / 2));
    }

    public static float inOutSine(float f){
        return (float) (- (Math.cos(Math.PI * f) - 1) / 2);
    }

    public static float inCubic(float f){
        return f * f * f;
    }

    public static float outCubic(float f){
        return (float) (1 - Math.pow(1 - f, 3));
    }

    public static float inOutCubic(float x){
        return (float) ((x < 0.5) ? 4 * x * x * x : 1 - Math.pow(- 2 * x + 2, 3) / 2);
    }

    public static float inQuad(float x){
        return (x * x);
    }

    public static float outQuad(float x){
        return (1 - (1 - x) * (1 - x));
    }

    public static float inOutQuad(float x){
        return (float) ((x < 0.5) ? 2 * x * x : 1 - Math.pow(- 2 * x + 2, 2) / 2);
    }

    public static float inQuart(float x){
        return (x * x * x * x);
    }

    public static float outQuart(float x){
        return (float) (1 - Math.pow(1 - x, 4));
    }

    public static float inOutQuart(float x){
        return (float) (x < 0.5 ? 8 * x * x * x * x : 1 - Math.pow(- 2 * x + 2, 4) / 2);
    }

    public static float inQuint(float x){
        return (x * x * x * x * x);
    }

    public static float outQuint(float x){
        return (float) (1 - Math.pow(1 - x, 5));
    }

    public static float inOutQuint(float x){
        return (float) (x < 0.5 ? 16 * x * x * x * x * x : 1 - Math.pow(- 2 * x + 2, 5) / 2);
    }

    public static float inExpo(float x){
        return (float) (x == 0 ? 0 : Math.pow(2, 10 * x - 10));
    }

    public static float outExpo(float x){
        return (float) (x == 1 ? 1 : 1 - Math.pow(2, - 10 * x));
    }

    public static float inOutExpo(float x){
        return (float) (x == 0 ? 0 : x == 1 ? 1 : x < 0.5 ? Math.pow(2, 20 * x - 10) / 2 : (2 - Math.pow(2, - 20 * x + 10)) / 2);
    }

    public static float inCirc(float x){
        return (float) (1 - Math.sqrt(1 - Math.pow(x, 2)));
    }

    public static float outCirc(float x){
        return (float) (Math.sqrt(1 - Math.pow(x - 1, 2)));
    }

    public static float inOutCirc(float x){
        return (float) (x < 0.5 ? (1 - Math.sqrt(1 - Math.pow(2 * x, 2))) / 2 : (Math.sqrt(1 - Math.pow(- 2 * x + 2, 2)) + 1) / 2);
    }

    public static float inBack(float x){
        return c3 * x * x * x - c1 * x * x;
    }

    public static float outBack(float x){
        return (float) (1 + c3 * Math.pow(x - 1, 3) + c1 * Math.pow(x - 1, 2));
    }

    public static float inOutBack(float x){
        return (float) (x < 0.5 ? (Math.pow(2 * x, 2) * ((c2 + 1) * 2 * x - c2)) / 2 : (Math.pow(2 * x - 2, 2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2);
    }

    public static float inElastic(float x){
        return (float) (x == 0 ? 0 : x == 1 ? 1 : - Math.pow(2, 10 * x - 10) * Math.sin((x * 10 - 10.75) * c4));
    }

    public static float outElastic(float x){
        return (float) (x == 0 ? 0 : x == 1 ? 1 : Math.pow(2, - 10 * x) * Math.sin((x * 10 - 0.75) * c4) + 1);
    }

    public static float inOutElastic(float x){
        return (float) (x == 0 ? 0 : x == 1 ? 1 : x < 0.5 ? - (Math.pow(2, 20 * x - 10) * Math.sin((20 * x - 11.125) * c5)) / 2 : (Math.pow(2, - 20 * x + 10) * Math.sin((20 * x - 11.125) * c5)) / 2 + 1);
    }

    public static float inBounce(float x){
        return 1 - outBounce(1 - x);
    }

    public static float outBounce(float x){
        if(x < 1 / d1){
            return n1 * x * x;
        }else if(x < 2 / d1){
            return (float) (n1 * (x -= 1.5 / d1) * x + 0.75);
        }else if(x < 2.5 / d1){
            return (float) (n1 * (x -= 2.25 / d1) * x + 0.9375);
        }else{
            return (float) (n1 * (x -= 2.625 / d1) * x + 0.984375);
        }
    }

    public static float inOutBounce(float x){
        return x < 0.5 ? (1 - outBounce(1 - 2 * x)) / 2 : (1 + outBounce(2 * x - 1)) / 2;
    }

    public static float easeOut(float t, float n, BiFunction<Float, Float, Float> function) {
        return 1 - function.apply(1 - t, n);
    }

    public static float easeInOut(float t, float n, BiFunction<Float, Float, Float> function) {
        if (t < 0.5d)
            return (float) (function.apply((float) (t * 2d), n) / 2d);

        return (float) (1 - function.apply((float) ((1 - t) * 2d), n) / 2d);
    }

    public static float bounce(float t, float n) {
        return (float) Math.min(Math.min(121f / 16f * t * t, 121f / 4f * n * Math.pow(t - 6f / 11f, 2) + 1 - n),
                Math.min(121 * n * n * Math.pow(t - 9f / 11f, 2) + 1 - n * n, 484 * n * n * n * Math.pow(t - 10.5f / 11f, 2) + 1 - n * n * n));
    }

    public static float elastic(float t, float n) {
        return (float) (1 - Math.pow(Math.cos(t * Math.PI / 2f), 3) * Math.cos(t * n * Math.PI));
    }

    public static float back(float t, float n) {
        final double n2 = n * 1.70158d;
        return (float) (t * t * ((n2 + 1) * t - n2));
    }
}
