package co.id.bca.cros.shared.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.util.Assert;

import java.util.Collection;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaginationSchema<T> {
    private final Collection<T> content;
    private final PageMetadata page;

    private PaginationSchema(Collection<T> content, long pageNo, long rowsPerPage, long totalElements) {
        this.content = content;
        this.page = new PageMetadata(pageNo, rowsPerPage, totalElements);
    }

    public static <T> PaginationSchema<T> of(Collection<T> content, long pageNo, long rowsPerPage, long totalElements) {
        return new PaginationSchema<>(content, pageNo, rowsPerPage, totalElements);
    }

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static class PageMetadata {
        private final long pageNo;
        private final long rowsPerPage;
        private final long totalPages;
        private final long totalElements;
        private final long firstItemNo;
        private final long lastItemNo;

        private PageMetadata(long pageNo, long rowsPerPage, long totalPages, long totalElements, long firstItemNo, long lastItemNo) {
            Assert.isTrue(pageNo > -1L, "Page no must not be negative!");
            Assert.isTrue(rowsPerPage > -1L, "Rows per page must not be negative!");
            Assert.isTrue(totalPages > -1L, "Total pages must not be negative!");
            Assert.isTrue(totalElements > -1L, "Total elements must not be negative!");
            Assert.isTrue(firstItemNo > -1L, "First item no must not be negative!");
            Assert.isTrue(lastItemNo > -1L, "Last item no must not be negative!");

            this.pageNo = pageNo;
            this.rowsPerPage = rowsPerPage;
            this.totalPages = totalPages;
            this.totalElements = totalElements;
            this.firstItemNo = firstItemNo;
            this.lastItemNo = lastItemNo;
        }

        private PageMetadata(long pageNo, long rowsPerPage, long totalElements) {
            this(
                    pageNo,
                    rowsPerPage,
                    rowsPerPage == 0L ? 0L : (long)Math.ceil((double)totalElements / (double)rowsPerPage),
                    totalElements,
                    Math.min((pageNo - 1) * rowsPerPage + 1, totalElements),
                    Math.min(rowsPerPage * pageNo, totalElements)
            );
        }
    }
}
