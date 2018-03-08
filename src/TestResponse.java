

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerem
 */
public class TestResponse {

    public final String body;
    public final int status;

    public TestResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }
}
