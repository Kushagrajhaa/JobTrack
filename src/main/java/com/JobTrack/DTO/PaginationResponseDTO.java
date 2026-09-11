package com.JobTrack.DTO;

import java.util.List;

public class PaginationResponseDTO {
    private List<JobApplicationResponseDTO> content;
    private int page;
    private Long totalElements;
    private int totalPages;
    private int size;

    public List<JobApplicationResponseDTO> getContent() {
        return content;
    }

    public void setContent(List<JobApplicationResponseDTO> content) {
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
