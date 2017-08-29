import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CharterauthComponent } from './charterauth.component';

describe('CharterauthComponent', () => {
  let component: CharterauthComponent;
  let fixture: ComponentFixture<CharterauthComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CharterauthComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CharterauthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
