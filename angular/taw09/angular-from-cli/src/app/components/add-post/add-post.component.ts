import { Component, Input, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.scss']
})
export class AddPostComponent implements OnInit {

  public post = {
    title: '',
    image: '',
    text: '',
  };
  constructor(private service : DataService) { }

  ngOnInit(): void {
  }

  createPost(){
    this.service.createPost(this.post).subscribe(result => {
      console.log("post added");
      return result;
    });
  }
}
