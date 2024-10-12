package br.com.fiap.coletalixo.util.converter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ListToPageConverter {

    public static <T> Page<T> converter(List<T> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());
        List<T> sublist = lista.subList(start, end);
        return new PageImpl<>(sublist, pageable, lista.size());
    }

}
