package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingService {

    private ParkingRepository repository;

    public ParkingService(ParkingRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Parking> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Parking findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        repository.save(parkingCreate);
        return parkingCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        repository.deleteById(id);

    }

    @Transactional
    public Parking update(String id, Parking parkingUpdate) {
        Parking parking = findById(id);
        parking.setColor(parkingUpdate.getColor());
        repository.save(parking);
        return parking;
    }

    public Parking exit(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        Double bill = ParkingCheckOut.getBill(parking);
        parking.setBill(bill);
        repository.save(parking);
        return parking;

    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
