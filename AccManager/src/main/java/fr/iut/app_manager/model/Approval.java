package fr.iut.loan_approval.model;

import com.googlecode.objectify.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Approval {
    enum Response {
        ACCEPTED,
        REFUSED
    }

    @Id
    private String uuid;
    @NotBlank(message = "Name must have a valid value")
    private String name;
    @NotBlank(message = "Uuid must have a valid value")
    private Response response;

    public Approval(){
        if (uuid == null) uuid = UUID.randomUUID().toString();
    }

    public Approval(UUID uuid, String name, Response response) {
        this.uuid = uuid.toString();
        this.name = name;
        this.response = response;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
