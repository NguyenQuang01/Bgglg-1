package com.example.itspower.type;

import java.util.regex.Pattern;

public class RegexType {
    public static final Pattern DATE_PATTERN =
            Pattern.compile("^((?:19|20)[0-9][0-9])([/-])(0?[1-9]|1[012])([/-])(0?[1-9]|[12][0-9]|3[01])");

}