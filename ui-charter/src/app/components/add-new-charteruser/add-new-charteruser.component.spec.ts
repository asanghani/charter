import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewCharteruserComponent } from './add-new-charteruser.component';

describe('AddNewCharteruserComponent', () => {
  let component: AddNewCharteruserComponent;
  let fixture: ComponentFixture<AddNewCharteruserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddNewCharteruserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewCharteruserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
