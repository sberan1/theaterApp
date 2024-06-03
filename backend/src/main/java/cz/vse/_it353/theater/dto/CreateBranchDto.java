package cz.vse._it353.theater.dto;

import lombok.Data;

/**
 * Data Transfer Object for creating a new branch.
 */
@Data
public class CreateBranchDto {
    /**
     * The name of the branch.
     */
    public String name;

    /**
     * The address of the branch.
     */
    public String address;

    /**
     * The version of the branch.
     */
    public Long version;
}