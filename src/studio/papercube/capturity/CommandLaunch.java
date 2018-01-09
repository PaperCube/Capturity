package studio.papercube.capturity;

import studio.papercube.library.argparser.Parameter;

class CommandLaunch {
    public static void main(String[] args) {
        Parameter p = Parameter.resolve(args);
        p.getMultipleValue("-i");
    }
}

