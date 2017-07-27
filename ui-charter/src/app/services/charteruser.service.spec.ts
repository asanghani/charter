import { TestBed, inject } from '@angular/core/testing';

import { CharteruserService } from './charteruser.service';

describe('CharteruserService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CharteruserService]
    });
  });

  it('should be created', inject([CharteruserService], (service: CharteruserService) => {
    expect(service).toBeTruthy();
  }));
});
