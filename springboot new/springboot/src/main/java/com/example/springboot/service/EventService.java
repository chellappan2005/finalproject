package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.springboot.repository.EventRepo;
import com.example.springboot.model.Event;
@Service
public class EventService {
     @Autowired
     EventRepo ev;
     public Event create(Event e){
      return ev.save(e);
     }
     public List<Event> getAll(){
      return ev.findAll();
     }
     public Event getId(int eid){
      return ev.findById(eid).orElse(null);
     }
     public boolean update(int eid,Event ee){
          if(this.getId(eid)==null)
          {
              return false;
          }
          try{
              ev.save(ee);
          }
          catch(Exception e)
          {
              return false;
          }
          return  true;
     }
     public boolean delete(int eid){
          if(this.getId(eid)==null){
             return false;
          }
          ev.deleteById(eid);
          return true;
     }
     public List<Event> page(int pageSize,int pageNumber)
    {
        Pageable page=PageRequest.of(pageNumber, pageSize);
        return ev.findAll(page).getContent();
    }
    public List<Event> sort(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return ev.findAll(sort);
    }
    public List<Event> getDetails(int s, String s1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDetails'");
    }
    public String deleteempdetails(int s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteempdetails'");
    }
    public String updateempdetails(int s, int s1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateempdetails'");
    }
    public Event findCustByName(String fname) {
        return ev.findEventByFirstName(fname);
    }
    

}