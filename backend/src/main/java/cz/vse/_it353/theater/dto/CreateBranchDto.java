package cz.vse._it353.theater.dto;

import lombok.Data;

@Data
public class CreateBranchDto {
    public String name;
    public String address;
    public Long version;
}
