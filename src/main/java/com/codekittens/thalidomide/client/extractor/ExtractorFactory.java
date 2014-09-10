package com.codekittens.thalidomide.client.extractor;

public class ExtractorFactory {

    public static ResponseExtractor createCsrfTokenExtractor() {
        return new ResponseExtractor("csrf_token", "'", 1);
    }

    public static ResponseExtractor createCommentsIdsExtractor() {
        return new ResponseExtractor("\" class=\"comment", "\"", 1);
    }

    public static ResponseExtractor createNumPaginatorPagesExtractor() {
        return new ResponseExtractor("new Paginator", ", ", 1);
    }

    public static ResponseExtractor create404Extractor() {
        return new ResponseExtractor("error/404");
    }

}
