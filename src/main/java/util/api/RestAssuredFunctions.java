package util.api;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredFunctions<T> {
    public Response getResponse(String url, String[] credential, Map<String, String> headerParameters,
                                Map<String, String> queryParameters, Map<String, String> proxies, T t, Method type) {
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecBuilder builder = getBuilderWithParameters(url, credential, headerParameters, queryParameters, proxies);
        return build(builder, t, type);
    }

    public RequestSpecBuilder getBuilderWithParameters(String url, String[] credential, Map<String, String> headerParameters,
                                                       Map<String, String> queryParameters, Map<String, String> proxies) {
        RequestSpecBuilder builder = new RequestSpecBuilder().setBaseUri(url);
        builder = setQueryParameters(builder, queryParameters);
        builder = setHeaderParameters(builder, headerParameters);
        builder = setCredential(builder, credential);
        builder = setProxy(builder, proxies);
        return builder;
    }

    public RequestSpecBuilder setQueryParameters(RequestSpecBuilder builder, Map<String, String> queryParameters) {
        if ((queryParameters != null) && !(queryParameters.isEmpty())) {
            builder = builder.addQueryParams(queryParameters);
        }
        return builder;
    }

    public RequestSpecBuilder setHeaderParameters(RequestSpecBuilder builder, Map<String, String> headerParameters) {
        if ((headerParameters != null) && !(headerParameters.isEmpty())) {
            builder = builder.addHeaders(headerParameters);
        }
        return builder;
    }

    public RequestSpecBuilder setCredential(RequestSpecBuilder builder, String[] credential) {
        if ((!(credential == null)) && (credential.length > 1)) {
            PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
            basicAuth.setUserName(credential[0]);
            basicAuth.setUserName(credential[1]);
            builder.setAuth(basicAuth);
        }
        return builder;
    }

    public RequestSpecBuilder setProxy(RequestSpecBuilder builder, Map<String, String> proxies) {
        if ((!(proxies == null)) && (!(proxies.isEmpty()))) {
            for (Map.Entry<String, String> entry : proxies.entrySet()) {
                System.out.println("Proxy key = "+ entry.getKey() + "Proxy value = "+entry.getValue());
                builder.setProxy(entry.getKey(), Integer.parseInt(entry.getValue()));
            }
        }
        return builder;
    }

    public Response build(RequestSpecBuilder builder, T t, Method type) {
        RequestSpecification specification;
        specification = builder.build();
        RestAssured.useRelaxedHTTPSValidation();
        if (type.equals(Method.GET)) {
            if (t == null)
                return given().spec(specification).get();
            return given().spec(specification).body(t).get();
        } else if (type.equals(Method.POST)) {
            return given().spec(specification).body(t).post();
        }
        return null;
    }

}
