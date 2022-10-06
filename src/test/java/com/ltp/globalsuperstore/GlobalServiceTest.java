package com.ltp.globalsuperstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Date;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GlobalServiceTest {

    @Mock
    private GlobalRepository globalRepository;

    @InjectMocks
    private GlobalService globalService;

    @Test
    public void ItemIndexFromIdTest(){
        Item item = new Item("Furniture","Couch",100d,10d,new java.util.Date(System.currentTimeMillis()));
        when(globalRepository.getItems()).thenReturn(Arrays.asList(item));
        when(globalRepository.getItem(0)).thenReturn(item);

        int valid = globalService.getIndexFromId(item.getId());
        int notFound = globalService.getIndexFromId("123");

        assertEquals(0,valid);
        assertEquals(Constants.NOT_FOUND,notFound);
    }

    @Test
    public void returnItemByIdTest(){
        Item item = new Item("Furniture","Couch",100d,10d,new java.util.Date(System.currentTimeMillis()));
        when(globalRepository.getItems()).thenReturn(Arrays.asList(item));
        when(globalRepository.getItem(0)).thenReturn(item);

        String id = item.getId();
        Item result = globalService.getItemFromId(id);
        assertEquals(item,result);
    }

    @Test
    public void addItemTest(){
        Item item = new Item("Furniture","Couch",100d,10d,new java.util.Date(System.currentTimeMillis()));
        when(globalRepository.getItems()).thenReturn(Arrays.asList(item));
        when(globalRepository.getItem(0)).thenReturn(item);

        Item newItem = new Item("Technology","Phone",300d,70d,new java.util.Date(System.currentTimeMillis()));
        globalService.addItem(newItem);
        verify(globalRepository,times(1)).addItem(newItem);
    }

    @Test
    public void updateItemTest(){
        Item item = new Item("Furniture","Couch",100d,10d,new java.util.Date(System.currentTimeMillis()));
        when(globalRepository.getItems()).thenReturn(Arrays.asList(item));
        when(globalRepository.getItem(0)).thenReturn(item);

        item.setDiscount(22d);
        globalService.handleSubmit(item);
        verify(globalRepository, times(1)).updateItem(item, 0);
    }
}
