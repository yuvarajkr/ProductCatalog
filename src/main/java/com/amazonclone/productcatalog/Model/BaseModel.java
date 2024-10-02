package com.amazonclone.productcatalog.Model;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public abstract class BaseModel {
    private long id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private Status status;
}
