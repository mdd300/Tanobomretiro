//
//  ViewController.swift
//  Tanobomretiro
//
//  Created by Victor Oshiro on 26/04/17.
//  Copyright © 2017 Victor Oshiro. All rights reserved.
//

import UIKit
import MapKit

class ViewController: UIViewController,MKMapViewDelegate, NSObject{

    var gerenciadorLocal = CLLocationManager()
    
    @IBOutlet weak var mapa: MKMapView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        
        let localizacao: CLLocationCoordinate2D = CLLocationCoordinate2DMake(-23.525108, -46.641265)
        let casa: CLLocationCoordinate2D = CLLocationCoordinate2DMake(-23.564093, -46.532219)
        let areaVisualizacao: MKCoordinateSpan = MKCoordinateSpanMake(0.04, 0.04)
        let regiao: MKCoordinateRegion = MKCoordinateRegionMake(localizacao, areaVisualizacao)
        
        mapa.setRegion( regiao , animated: true)
        
        let anotacao = MKPointAnnotation()
        
        
        
        let url = URL(string: "http://localhost:8888/conexao.php")!
        let request = URLRequest(url: url)
        
        // modify the request as necessary, if necessary
        
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            guard let data = data else {
                print("request failed \(error)")
                return
            }
            
            do {
                if let json = try JSONSerialization.jsonObject(with: data) as? [String: String], let name = json["name"] {
                    //configurar
                    anotacao.coordinate = localizacao
                    anotacao.title = "Teste"
                    anotacao.subtitle = "teste"
                    
                    
                }
            } catch let parseError {
                print("parsing error: \(parseError)")
                let responseString = String(data: data, encoding: .utf8)
                print("raw response: \(responseString)")
            }
        }
        task.resume()
        mapa.addAnnotation(anotacao)
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

