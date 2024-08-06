package payload.pojo.response.addPet.response.addPet;

import payload.pojo.request.addPet.Category;
import payload.pojo.request.addPet.Tags;

public class AddPetResponse {
    private long id;
    private payload.pojo.request.addPet.Category category;
    private String name;
    private String[] photoUrls;
    private payload.pojo.request.addPet.Tags[] tags;
    private String status;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public payload.pojo.request.addPet.Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public payload.pojo.request.addPet.Tags[] getTags() {
        return tags;
    }

    public void setTags(Tags[] tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
    }
}

